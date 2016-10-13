package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaMesario;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaResultadoEleicao;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaUrna;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class ControladorUrna implements IControlador<Urna> {

    public final ControladorPrincipal controladorPrincipal;
    private final ArrayList<Urna> urnas;

    public ControladorUrna(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.urnas = new ArrayList<>();
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

    public int verificaEleitor(Urna urna, Eleitor eleitor) {
        if (urna.getLimiteDeEleitores() > urna.getTotalDeVotosEfetuados()) {
            if (eleitor.getZonaEleitoral() == urna.getZonaEleitoral()
                    && eleitor.getSecaoEleitoral() == urna.getSecaoEleitoral()
                    && eleitor.getCidade() == urna.getCidade()) {
                return 0; //Sem erros
            }
            return 2; //Erro: Eleitor esta vontado na urna errada
        }
        return 1; //Erro: Eleitor não pode votar pois a urna atingiu seu limite
    }

    public <T> LinkedHashMap<T, Integer> getVariavelMaisVotada(LinkedHashMap<T, Integer> totalDeVotosDoHashMap) {
        LinkedHashMap<T, Integer> temp = new LinkedHashMap<T, Integer>(totalDeVotosDoHashMap);
        LinkedHashMap<T, Integer> variavelOrdenada = new LinkedHashMap<T, Integer>();
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

    public void iniciaEleicoes() {
        for (Urna urna : urnas) {
            urna.inicia();
            TelaMesario tela = new TelaMesario(this, urna);
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
