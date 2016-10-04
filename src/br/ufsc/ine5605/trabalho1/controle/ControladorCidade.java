package br.ufsc.ine5605.trabalho1.controle;

import java.util.Collection;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaCidade;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ControladorCidade {

	public final ControladorPrincipal controladorPrincipal;

	private ArrayList<Cidade> cidades;

	private TelaCidade telaCidade;

	public ControladorCidade(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
	}

	public void cadastraCidade(String nome) {

	}

	public void removeCidade(Cidade cidade) {

	}

	public Cidade getCidade(String nome) {
		return null;
	}

	public ArrayList<Cidade> getCidades() {
		return null;
	}

        //Não alterar
	public void exibeTelaCidade() {
            TelaCidade tc = new TelaCidade(this);
            tc.setVisible(true);
	}

}
