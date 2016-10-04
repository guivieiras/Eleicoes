package br.ufsc.ine5605.trabalho1.entidade;

import java.util.HashMap;

public class Urna {

	private HashMap<Candidato,Integer> totalDeVotosPorCandidato;

	private int secaoEleitoral;

	private int zonaEleitoral;

	private Cidade cidade;

	private int limiteDeVotos;

	private int votosEfetuados;

	public Urna(int limiteDeVotos, int secaoEleitoral, int zonaEleitoral, Cidade cidade, HashMap<Candidato,Integer> candidatos) {

	}

	public void contabilizaVoto(int codigoPrefeito, int codigoVereador) {

	}

	public HashMap<Candidato,Integer> getTotalDeVotosPorCandidato() {
		return null;
	}

	public void getCidade() {

	}

}
