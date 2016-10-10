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
            this.cidades = new ArrayList<>();
	}

	public boolean cadastraCidade(Cidade cidade) {
           cidades.add(cidade);
           return true;
	}

	public void removeCidade(Cidade cidade) {

	}
        
        public void modificaCidade(Cidade cidade, String nome){
            
        }

	public Cidade getCidade(String nome) {
		return null;
	}

	public ArrayList<Cidade> getCidades() {
		return cidades;
	}

	public void exibeTelaCidade() {
            TelaCidade tc = new TelaCidade(this);
            tc.setLocationRelativeTo(null);
            tc.setVisible(true);
	}

}
