package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaCandidato;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ControladorCandidato {

	public final ControladorPrincipal controladorPrincipal;
	private TelaCandidato telaCandidato;
	private ArrayList<Candidato> candidatos;

	public ControladorCandidato(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
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

        //Não alterar
	public void exibeTelaCandidatos() {
            TelaCandidato tc = new TelaCandidato(this);
            tc.setVisible(true);
	}

}
