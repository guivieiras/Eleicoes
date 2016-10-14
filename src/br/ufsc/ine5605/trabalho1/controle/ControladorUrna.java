package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaMesario;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaResultadoEleicao;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaUrna;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.entidade.KeyValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class ControladorUrna implements IControlador<Urna> {

    public final ControladorPrincipal controladorPrincipal;
    private final ArrayList<Urna> urnas;
    private int vagasParaVereadores;

    public int getVagasParaVereadores() {
        return vagasParaVereadores;
    }

    public ControladorUrna(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.urnas = new ArrayList<>();
        this.vagasParaVereadores = 3;
    }

    @Override
    public boolean cadastra(Urna novaUrna) {
        for (Urna urna : urnas) {
            if (novaUrna.getCidade() == urna.getCidade()
                    && novaUrna.getZonaEleitoral() == urna.getZonaEleitoral()
                    && novaUrna.getSecaoEleitoral() == urna.getSecaoEleitoral()) {
                return false;
            }
        }
        return urnas.add(novaUrna);
    }

    @Override
    public boolean remove(Urna urna) {
        return urnas.remove(urna);
    }

    @Override
    public boolean modifica(Urna nova, Urna antiga) {
        return false;
    }

    @Override
    public ArrayList<Urna> getLista() {
        return urnas;
    }

    public ArrayList<Urna> getLista(Cidade cidade) {
        ArrayList<Urna> temp = new ArrayList<>();

        for (Urna urna : urnas) {
            if (urna.getCidade() == cidade) {
                temp.add(urna);
            }
        }

        return temp;
    }

    public Urna getUrna(int secao, int zona, Cidade cidade) {
        for (Urna urna : urnas) {
            if (urna.getSecaoEleitoral() == secao && urna.getZonaEleitoral() == zona && urna.getCidade() == cidade) {
                return urna;
            }
        }
        return null;
    }

    public int verificaEleitor(Urna urna, Eleitor eleitor) {
        if (urna.getLimiteDeEleitores() > urna.getTotalDeVotosEfetuados()) {
            if (eleitor.getZonaEleitoral() == urna.getZonaEleitoral()
                    && eleitor.getSecaoEleitoral() == urna.getSecaoEleitoral()
                    && eleitor.getCidade() == urna.getCidade()) {
                return 0; //Sem erros
            }
            return 2; //Erro: Eleitor esta vontado na urna errada
        }
        return 1; //Erro: Eleitor nao pode votar pois a urna atingiu seu limite
    }

    public <T> LinkedHashMap<T, Integer> ordenaHashMap(LinkedHashMap<T, Integer> totalDeVotosDoHashMap) {
        LinkedHashMap<T, Integer> temp = new LinkedHashMap<>(totalDeVotosDoHashMap);
        LinkedHashMap<T, Integer> variavelOrdenada = new LinkedHashMap<>();
        int tamanhoInicial = temp.size();
        for (int i = 0; i < tamanhoInicial; i++) {
            T variavel = null;
            int votos = -1;
            for (Entry<T, Integer> entry : temp.entrySet()) {
                if (votos < entry.getValue()) {
                    variavel = entry.getKey();
                    votos = entry.getValue();
                }
            }
            temp.remove(variavel);
            variavelOrdenada.put(variavel, votos);
        }
        return variavelOrdenada;
    }

    public KeyValue<Candidato, Integer> prefeitoVencedor(Cidade cidade) {
        LinkedHashMap<Candidato, Integer> listaVotosPrefeitos = new LinkedHashMap<>();
        LinkedHashMap<Candidato, Integer> votosOrdenados = new LinkedHashMap<>();
        for (Urna urna : urnas) {
            if (urna.getCidade() == cidade) {
                listaVotosPrefeitos = urna.getTotalDeVotosPorPrefeito();
                for (Entry<Candidato, Integer> entry : listaVotosPrefeitos.entrySet()) {
                    if (!votosOrdenados.containsKey(entry.getKey())) {
                        votosOrdenados.put(entry.getKey(), entry.getValue());
                    } else {
                        votosOrdenados.put(entry.getKey(), votosOrdenados.get(entry.getKey()) + entry.getValue());
                    }
                }
            }
        }
        votosOrdenados = ordenaHashMap(votosOrdenados);

        ArrayList<Entry<Candidato, Integer>> lista = new ArrayList<>(votosOrdenados.entrySet());

        return new KeyValue(lista.get(0).getKey(), lista.get(0).getValue());
    }

    
    public LinkedHashMap<Candidato, Integer> vereadorVencedor(Cidade cidade) {

        LinkedHashMap<Candidato, Integer> listaVotosVereadores = new LinkedHashMap<>();
        LinkedHashMap<Partido, Integer> listaVotosPorPartido = new LinkedHashMap<>();
        LinkedHashMap<Candidato, Integer> votosOrdenadosVereadores = new LinkedHashMap<>();
        LinkedHashMap<Partido, Integer> votosOrdenadosPartidos = new LinkedHashMap<>();

        for (Urna urna : getLista(cidade)) {

            listaVotosVereadores = urna.getTotalDeVotosPorVereador();
            for (Entry<Candidato, Integer> entry : listaVotosVereadores.entrySet()) {
                if (!votosOrdenadosVereadores.containsKey(entry.getKey())) {
                    votosOrdenadosVereadores.put(entry.getKey(), entry.getValue());
                } else {
                    votosOrdenadosVereadores.put(entry.getKey(), votosOrdenadosVereadores.get(entry.getKey()) + entry.getValue());
                }
            }
        }

        for (Urna urna : getLista(cidade)) {
                listaVotosPorPartido = urna.getTotalDeVotosPorPartidoParaVereador();
                for (Entry<Partido, Integer> entry : listaVotosPorPartido.entrySet()) {
                    if (!votosOrdenadosPartidos.containsKey(entry.getKey())) {
                        votosOrdenadosPartidos.put(entry.getKey(), entry.getValue());
                    } else {
                        votosOrdenadosPartidos.put(entry.getKey(), votosOrdenadosPartidos.get(entry.getKey()) + entry.getValue());
                    }
                }
            }
        
        int totalDeVotosValidos = 0;
        for (Urna urna : getLista(cidade)) {
                totalDeVotosValidos += urna.getVotosValidosParaVerador();
            
        }
        int quocienteEleitoral = Math.round((float)totalDeVotosValidos / getVagasParaVereadores());
        votosOrdenadosVereadores = ordenaHashMap(votosOrdenadosVereadores);
        votosOrdenadosPartidos = ordenaHashMap(votosOrdenadosPartidos);
        int[] quocientePartidario = new int[votosOrdenadosPartidos.size()];
        int i = 0;

        for (Entry<Partido, Integer> entry : votosOrdenadosPartidos.entrySet()) {
            quocientePartidario[i] = (entry.getValue() / quocienteEleitoral);
            i++;
        }
        
        
        int soma = 0;
        for (int qp : quocientePartidario)
        {
            soma += qp;
        }
        while (soma < getVagasParaVereadores())
        {
            ArrayList<Double> medias = new ArrayList<>();
            
            
            int j = 0;
            for (Entry<Partido, Integer> entry :  votosOrdenadosPartidos.entrySet())
            {
                medias.add(0d);
                medias.set(j , (double)entry.getValue() / quocientePartidario[j] + 1);
                j++;
            }
            double maior  = Collections.max(medias);
            quocientePartidario[medias.indexOf(maior)]++;
            
            soma++;
        }
        
        i = 0;
        LinkedHashMap<Candidato, Integer> vereadoresEleitos = new LinkedHashMap<>();
        for (Entry<Partido, Integer> entryPartido : votosOrdenadosPartidos.entrySet()) {
            if (quocientePartidario[i] == 0) {
                break;
            }
            for (Entry<Candidato, Integer> entryVereador : votosOrdenadosVereadores.entrySet()) {
                if (entryVereador.getKey().getPartido() == entryPartido.getKey()) {
                    if (quocientePartidario[i] > 0) {
                        vereadoresEleitos.put(entryVereador.getKey(), entryVereador.getValue());
                        quocientePartidario[i]--;
                    }
                }
            }
            i++;
        }
        return ordenaHashMap(vereadoresEleitos);
    }

    public void iniciaEleicoes() {
        int i = 50;
        for (Urna urna : urnas) {
            urna.inicia();
            TelaMesario tela = new TelaMesario(this, urna);

            tela.setLocation(i, 200);
            i += 340;
            tela.setVisible(true);
        }
    }

    public void exibeTelaResultado() {
        TelaResultadoEleicao tre = new TelaResultadoEleicao(this);
        tre.setVisible(true);
    }

    @Override
    public void exibeTela() {
        TelaUrna tela = new TelaUrna(this);
        tela.setVisible(true);
    }

    public int urnasEmExecucao() {
        int qtdUrnas = 0;
        for (Urna urna : urnas) {
            if (urna.estaExecutando()) {
                qtdUrnas++;
            }
        }
        return qtdUrnas;
    }
}
