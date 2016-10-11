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
	private ArrayList<Candidato> candidatos;

	public ControladorCandidato(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
	}

	public void removeCandidato(int codigo) {

	}
        
        public void modificaCandidato(Candidato candidato, int numero, String nome, Cargo cargo, Cidade cidade, Partido partido){
            
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

    public void cadastraCandidato(Candidato candidato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
