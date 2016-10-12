package br.ufsc.ine5605.trabalho1.entidade;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaMesario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaVotacao;

public class Urna {

    private final HashMap<Candidato, Integer> totalDeVotosPorPrefeito;
    private final HashMap<Partido, Integer> totalDeVotosPorPartidoParaVereador;
    private int secaoEleitoral;
    private int zonaEleitoral;
    private Cidade cidade;
    private int numeroEleitores;
    private int votosEfetuadosParaPrefeito;
    private int votosBrancosParaPrefeito;
    private int votosNulosParaPrefeito;
    private int votosEfetuadosParaVereador;
    private int votosBrancosParaVereador;
    private int votosNulosParaVereador;

    public Urna(int numeroEleitores, int secaoEleitoral, int zonaEleitoral, Cidade cidade, ArrayList<Candidato> candidatos, ArrayList<Partido> partidos) {
        votosEfetuadosParaPrefeito = 0;
        votosEfetuadosParaVereador = 0;
        votosBrancosParaPrefeito = 0;
        votosBrancosParaVereador = 0;
        votosNulosParaPrefeito = 0;
        votosNulosParaVereador = 0;
        setSecaoEleitoral(secaoEleitoral);
        setNumeroEleitores(numeroEleitores);
        setZonaEleitoral(zonaEleitoral);
        setCidade(cidade);
        totalDeVotosPorPrefeito = new HashMap<>();
        for (Candidato candidato : candidatos) {
            totalDeVotosPorPrefeito.put(candidato, 0);
        }
        totalDeVotosPorPartidoParaVereador = new HashMap<>();
        for (Partido partido : partidos) {
            totalDeVotosPorPartidoParaVereador.put(partido, 0);
        }
    }

    public void contabilizaVoto(int codigoPrefeito, int codigoVereador) {
        Candidato prefeito = getCandidatoPorCodigo(codigoPrefeito);
        Candidato vereador = getCandidatoPorCodigo(codigoVereador);
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
            totalDeVotosPorPartidoParaVereador.put(vereador.getPartido(), totalDeVotosPorPartidoParaVereador.get(vereador) + 1);
        } else {
            votosNulosParaVereador++;
        }
    }

    public Candidato getCandidatoPorCodigo(int codigo) {
        for (Entry<Candidato, Integer> entry : totalDeVotosPorPrefeito.entrySet()) {
            Candidato key = entry.getKey();
            if (key.getNumero() == codigo) {
                return key;
            }
        }
        return null;
    }

    public void exibeTelaMesario() {
        TelaMesario tela = new TelaMesario(this);
        tela.setVisible(true);
    }

    public boolean verificaEleitor(Eleitor eleitor) {
        if (numeroEleitores < votosEfetuadosParaVereador) {
            if (eleitor.getZonaEleitoral() == this.zonaEleitoral && eleitor.getSecaoEleitoral() == this.secaoEleitoral) {
                return true;
            }
        }
        return false;
    }

    //
    //      Aqui começam os get and setters da classe
    //
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

    public int getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public int getZonaEleitoral() {
        return zonaEleitoral;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public int getVotosInvalidosParaVerador() {
        return (this.votosNulosParaVereador - this.votosEfetuadosParaVereador + (this.numeroEleitores) + this.votosBrancosParaVereador);
    }

    public int getVotosInvalidosParaPrefeito() {
        return (this.votosNulosParaPrefeito - this.votosEfetuadosParaPrefeito + (this.numeroEleitores) + this.votosBrancosParaPrefeito);
    }

    private void setZonaEleitoral(int zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    private void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    private void setNumeroEleitores(int numeroEleitores) {
        this.numeroEleitores = numeroEleitores;
    }

    private void setSecaoEleitoral(int secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }
}
