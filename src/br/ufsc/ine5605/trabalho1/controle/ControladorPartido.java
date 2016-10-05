package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaPartido;
import java.util.ArrayList;

public class ControladorPartido {

	public final ControladorPrincipal controladorPrincipal;
	private ArrayList<Partido> partidos;

	public ControladorPartido(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
	}

	public void cadastraPartido(String nome, String sigla) {

	}

	public void removePartido(Partido partido) {

	}
        public void modificaPartido(Partido partido, String nome, String sigla){
            
        }

	public ArrayList<Partido> getPartidos() {
		return null;
	}

	public Partido getPartido(String nome) {
		return null;
	}

	public void exibeTelaPartido() {

	}

}
