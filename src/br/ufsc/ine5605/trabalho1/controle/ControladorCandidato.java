package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaCandidato;
import java.util.Collection;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import java.util.ArrayList;

public class ControladorCandidato {

	private ControladorPrincipal controladorPrincipal;

	private TelaCandidato telaCandidato;

	private Collection<Candidato> candidatos;

	public ControladorCandidato(ControladorPrincipal controladorPrincipal) {

	}

	public void cadastraCandidato(int numero, String nome, Cargo cargo, Cidade cidade, Partido partido) {

	}

	public void removeCandidato(int codigo) {

	}

	public ArrayList<Candidato> getCandidatos() {
		return null;
	}

	public Candidato getCandidato(int codigo) {
		return null;
	}

	public ArrayList<Candidato> getCandidatos(Cidade cidade) {
		return null;
	}

	public void exibeTelaCandidatos() {

	}

}
