package br.ufsc.ine5605.trabalho1.controle;

import java.util.Collection;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaUrna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaVotacao;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import java.util.ArrayList;

public class ControladorUrna {

	private ControladorPrincipal controladorPrincipal;

	private Collection<Urna> urnas;

	private TelaUrna telaUrna;

	private TelaVotacao telaVotacao;

	public ControladorUrna(ControladorPrincipal controladorPrincipal) {

	}

	public void votar(int titulo, int prefeito, int vereador, Urna urna) {

	}

	public void cadastraUrna(Cidade cidade, int limiteDeVotos, int zonaEleitoral, int secaoEleitoral) {

	}

	public void removeUrna(Urna urna) {

	}

	public ArrayList<Urna> getUrnas() {
		return null;
	}

	public void exibeTelaVotacao() {

	}

	public void exibeTelaUrna() {

	}

}
