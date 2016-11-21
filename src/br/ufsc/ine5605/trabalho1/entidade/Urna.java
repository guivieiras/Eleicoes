package br.ufsc.ine5605.trabalho1.entidade;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.io.Serializable;
import java.util.LinkedHashMap;

public class Urna implements Serializable {

    public enum Turno {
        Primeiro, Segundo
    }

    public enum Estado {
        Aberta, Executando, Encerrada
    }

    private final LinkedHashMap<Candidato, Integer> totalDeVotosPorPrefeito;
    private final LinkedHashMap<Candidato, Integer> totalDeVotosPorVereador;
    private final LinkedHashMap<Partido, Integer> totalDeVotosPorPartidoParaVereador;
    private final int secaoEleitoral;
    private final int zonaEleitoral;
    private final Cidade cidade;
    private final int limiteDeEleitores;
    private int votosEfetuadosParaPrefeito;
    private int votosBrancosParaPrefeito;
    private int votosNulosParaPrefeito;
    private int votosEfetuadosParaVereador;
    private int votosBrancosParaVereador;
    private int votosNulosParaVereador;
    private int totalDeCandidatos;
    private final int codigo;

    private Estado estado = Estado.Aberta;

    private final Turno turno;

    public Urna(int limiteDeEleitores, int secaoEleitoral, int zonaEleitoral, Cidade cidade, ArrayList<Candidato> candidatos, Turno turno) {
        this.turno = turno;
        codigo = hashCode();

        votosEfetuadosParaPrefeito = 0;
        votosEfetuadosParaVereador = 0;
        votosBrancosParaPrefeito = 0;
        votosBrancosParaVereador = 0;
        votosNulosParaPrefeito = 0;
        votosNulosParaVereador = 0;

        this.secaoEleitoral = secaoEleitoral;
        this.limiteDeEleitores = limiteDeEleitores;
        this.zonaEleitoral = zonaEleitoral;
        this.cidade = cidade;

        totalDeVotosPorPrefeito = new LinkedHashMap<>();
        totalDeVotosPorVereador = new LinkedHashMap<>();
        totalDeVotosPorPartidoParaVereador = new LinkedHashMap<>();

        for (Candidato candidato : candidatos) {
            if (candidato.getCargo() == Cargo.Prefeito) {
                totalDeVotosPorPrefeito.put(candidato, 0);
                totalDeCandidatos++;
            } else if (turno == Turno.Primeiro) {
                totalDeVotosPorPartidoParaVereador.put(candidato.getPartido(), 0);
                totalDeVotosPorVereador.put(candidato, 0);
                totalDeCandidatos++;
            }
        }
    }

    public void inicia() {
        estado = Estado.Executando;
    }

    public void encerra() {
        estado = Estado.Encerrada;
    }

    public void contabilizaVoto(int codigoPrefeito, int codigoVereador) {
        Candidato prefeito = getPrefeitoPorCodigo(codigoPrefeito);
        Candidato vereador = getVereadorPorCodigo(codigoVereador);

        if (prefeito != null && (prefeito.getCargo() == Cargo.Vereador || !prefeito.getCidade().equals(getCidade()))) {
            prefeito = null;
        }
        if (vereador != null && (vereador.getCargo() == Cargo.Prefeito || !vereador.getCidade().equals(getCidade()))) {
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

    public void contabilizaVoto(int codigoPrefeito) {
        Candidato prefeito = getPrefeitoPorCodigo(codigoPrefeito);
        if (prefeito != null && (prefeito.getCargo() == Cargo.Vereador || !prefeito.getCidade().equals(getCidade()))) {
            prefeito = null;
        }
        votosEfetuadosParaPrefeito++;
        if (codigoPrefeito == 00) {
            votosBrancosParaPrefeito++;
        } else if (prefeito != null) {
            totalDeVotosPorPrefeito.put(prefeito, totalDeVotosPorPrefeito.get(prefeito) + 1);
        } else {
            votosNulosParaPrefeito++;
        }
    }

    public void zerar() {
        votosEfetuadosParaPrefeito = 0;
        votosEfetuadosParaVereador = 0;
        votosBrancosParaPrefeito = 0;
        votosBrancosParaVereador = 0;
        votosNulosParaPrefeito = 0;
        votosNulosParaVereador = 0;

        for (Entry<Candidato, Integer> entry : totalDeVotosPorVereador.entrySet()) {
            entry.setValue(0);
        }
        for (Entry<Candidato, Integer> entry : totalDeVotosPorPrefeito.entrySet()) {
            entry.setValue(0);
        }
        for (Entry<Partido, Integer> entry : totalDeVotosPorPartidoParaVereador.entrySet()) {
            entry.setValue(0);
        }
        
        estado = Estado.Aberta;
    }

    //
    //      Aqui comecam os get and setters da classe
    //
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

    public Estado getEstado() {
        return estado;
    }

    public Turno getTurno() {
        return turno;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getTotalDeCandidatos() {
        return totalDeCandidatos;
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

    public int getTotalDeVotosEfetuados() {
        return votosEfetuadosParaPrefeito;
    }

    public int getVotosValidosParaVerador() {
        return (-votosNulosParaVereador + votosEfetuadosParaVereador - votosBrancosParaVereador);
    }

    public int getVotosBrancosParaVereador() {
        return votosBrancosParaVereador;
    }

    public int getVotosNulosParaVereador() {
        return votosNulosParaVereador;
    }

    public int getVotosInvalidosParaVerador() {
        return (votosNulosParaVereador + votosBrancosParaVereador);
    }

    public int getVotosBrancosParaPrefeito() {
        return votosBrancosParaPrefeito;
    }

    public int getVotosNulosParaPrefeito() {
        return votosNulosParaPrefeito;
    }

    public int getVotosInvalidosParaPrefeito() {
        return (votosNulosParaPrefeito + votosBrancosParaPrefeito);
    }

    public int getAbstencoes() {
        return limiteDeEleitores - votosEfetuadosParaPrefeito;
    }

    public int getLimiteDeEleitores() {
        return limiteDeEleitores;
    }
}
