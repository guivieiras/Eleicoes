package br.ufsc.ine5605.trabalho1.entidade;

import java.util.ArrayList;
import java.util.Map.Entry;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaVotacao;
import java.util.LinkedHashMap;

public class Urna {
    public enum Turno{
        Primeiro, Segundo
    }

    private final LinkedHashMap<Candidato, Integer> totalDeVotosPorPrefeito;
    private final LinkedHashMap<Candidato, Integer> totalDeVotosPorVereador;
    private final LinkedHashMap<Partido, Integer> totalDeVotosPorPartidoParaVereador;
    private int secaoEleitoral;
    private int zonaEleitoral;
    private Cidade cidade;
    private int limiteDeEleitores;
    private int votosEfetuadosParaPrefeito;
    private int votosBrancosParaPrefeito;
    private int votosNulosParaPrefeito;
    private int votosEfetuadosParaVereador;
    private int votosBrancosParaVereador;
    private int votosNulosParaVereador;

    private boolean executando;
    private Turno turno;

    public Urna(int limiteDeEleitores, int secaoEleitoral, int zonaEleitoral, Cidade cidade, ArrayList<Candidato> candidatos, Turno turno) {
        this.turno = turno;
        votosEfetuadosParaPrefeito = 0;
        votosEfetuadosParaVereador = 0;
        votosBrancosParaPrefeito = 0;
        votosBrancosParaVereador = 0;
        votosNulosParaPrefeito = 0;
        votosNulosParaVereador = 0;
        setSecaoEleitoral(secaoEleitoral);
        setLimiteDeEleitores(limiteDeEleitores);
        setZonaEleitoral(zonaEleitoral);
        setCidade(cidade);
        totalDeVotosPorPrefeito = new LinkedHashMap<>();
        totalDeVotosPorVereador = new LinkedHashMap<>();
        totalDeVotosPorPartidoParaVereador = new LinkedHashMap<>();
        for (Candidato candidato : candidatos) {
            if (candidato.getCargo() == Cargo.Prefeito) {
                totalDeVotosPorPrefeito.put(candidato, 0);
            } else if (candidato.getCargo() == Cargo.Vereador) {
                totalDeVotosPorPartidoParaVereador.put(candidato.getPartido(), 0);
                totalDeVotosPorVereador.put(candidato, 0);
            }
        }
    }

    public void inicia() {
        executando = true;
    }

    public void encerra() {
        executando = false;
    }

    public boolean estaExecutando() {
        return executando;
    }

    public boolean isExecutando() {
        return executando;
    }

    public void contabilizaVoto(int codigoPrefeito, int codigoVereador) {
        Candidato prefeito = getPrefeitoPorCodigo(codigoPrefeito);
        Candidato vereador = getVereadorPorCodigo(codigoVereador);

        if (prefeito != null && (prefeito.getCargo() == Cargo.Vereador || prefeito.getCidade() != getCidade())) {
            prefeito = null;
        }
        if (vereador != null && (vereador.getCargo() == Cargo.Prefeito|| vereador.getCidade() != getCidade())) {
            vereador = null;
        }

        votosEfetuadosParaPrefeito++;
        if (codigoPrefeito == 00) {
            votosBrancosParaPrefeito++;
        } else if (prefeito != null) {
            totalDeVotosPorPrefeito.put(prefeito, totalDeVotosPorPrefeito.get(prefeito) + 1);
        } else {
            votosNulosParaPrefeito++;
        }
        votosEfetuadosParaVereador++;
        if (codigoVereador == 00) {
            votosBrancosParaVereador++;
        } else if (vereador != null) {
            totalDeVotosPorPartidoParaVereador.put(vereador.getPartido(), totalDeVotosPorPartidoParaVereador.get(vereador.getPartido()) + 1);
            totalDeVotosPorVereador.put(vereador, totalDeVotosPorVereador.get(vereador) + 1);
        } else {
            votosNulosParaVereador++;
        }
    }

    public Candidato getPrefeitoPorCodigo(int codigo) {
        for (Entry<Candidato, Integer> entry : totalDeVotosPorPrefeito.entrySet()) {
            Candidato key = entry.getKey();
            if (key.getNumero() == codigo) {
                return key;
            }
        }
        return null;
    }

    public Candidato getVereadorPorCodigo(int codigo) {
        for (Entry<Candidato, Integer> entry : totalDeVotosPorVereador.entrySet()) {
            Candidato key = entry.getKey();
            if (key.getNumero() == codigo) {
                return key;
            }
        }
        return null;
    }

    //
    //      Aqui comecam os get and setters da classe
    //
    public Turno getTurno() {
        return turno;
    }
    
    public int getVotosPorPartido(Partido partido) {
        if (totalDeVotosPorPartidoParaVereador.containsKey(partido)) {
            return totalDeVotosPorPartidoParaVereador.get(partido);
        }
        return -1;
    }

    public int getVotosPorPrefeito(Candidato prefeito) {
        if (totalDeVotosPorPrefeito.containsKey(prefeito)) {
            return totalDeVotosPorPrefeito.get(prefeito);
        }
        return -1;
    }

    public int getVotosPorVereador(Candidato vereador) {
        if (totalDeVotosPorVereador.containsKey(vereador)) {
            return totalDeVotosPorVereador.get(vereador);
        }
        return -1;
    }

    public LinkedHashMap<Candidato, Integer> getTotalDeVotosPorPrefeito() {
        return totalDeVotosPorPrefeito;
    }

    public LinkedHashMap<Candidato, Integer> getTotalDeVotosPorVereador() {
        return totalDeVotosPorVereador;
    }

    public LinkedHashMap<Partido, Integer> getTotalDeVotosPorPartidoParaVereador() {
        return totalDeVotosPorPartidoParaVereador;
    }

    public int getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public int getZonaEleitoral() {
        return zonaEleitoral;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public int getVotosValidosParaVerador() {
        return (-getVotosNulosParaVereador() + getVotosEfetuadosParaVereador() - getVotosBrancosParaVereador());
    }

    public int getVotosEfetuadosParaPrefeito() {
        return votosEfetuadosParaPrefeito;
    }

    public int getVotosBrancosParaPrefeito() {
        return votosBrancosParaPrefeito;
    }

    public int getVotosNulosParaPrefeito() {
        return votosNulosParaPrefeito;
    }

    public int getVotosEfetuadosParaVereador() {
        return votosEfetuadosParaVereador;
    }

    public int getVotosBrancosParaVereador() {
        return votosBrancosParaVereador;
    }

    public int getVotosNulosParaVereador() {
        return votosNulosParaVereador;
    }

    public int getVotosInvalidosParaVerador() {
        return (this.votosNulosParaVereador + this.votosBrancosParaVereador);
    }

    public int getTotalDeVotosEfetuados() {
        return votosEfetuadosParaPrefeito;
    }

    public int getVotosInvalidosParaPrefeito() {
        return (this.votosNulosParaPrefeito  + this.votosBrancosParaPrefeito);
    }
    
    public int getAbstencoes()
    {
        return limiteDeEleitores - votosEfetuadosParaPrefeito;
    }

    public int getLimiteDeEleitores() {
        return limiteDeEleitores;
    }

    private void setZonaEleitoral(int zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    private void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    private void setLimiteDeEleitores(int limiteDeEleitores) {
        this.limiteDeEleitores = limiteDeEleitores;
    }

    private void setSecaoEleitoral(int secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }
}
