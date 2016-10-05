package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaCidade;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ControladorCidade {

	public final ControladorPrincipal controladorPrincipal;
	private ArrayList<Cidade> cidades;

	public ControladorCidade(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
	}

	public void cadastraCidade(String nome) {

	}

	public void removeCidade(Cidade cidade) {

	}
        
        public void modificaCidade(Cidade cidade, String nome){
            
        }

	public Cidade getCidade(String nome) {
		return null;
	}

	public ArrayList<Cidade> getCidades() {
		return null;
	}

	public void exibeTelaCidade() {

	}

}
