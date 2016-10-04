package br.ufsc.ine5605.trabalho1.controle;

import java.util.Collection;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaEleitor;
import java.util.ArrayList;

/**
 *  
 *  @author 10349509913 
 * 
 */
public class ControladorEleitor {

	private final Collection<Eleitor> eleitores = new ArrayList<>();

	private TelaEleitor telaEleitor;

	private ControladorPrincipal controladorPrincipal;

	public ControladorEleitor(ControladorPrincipal controladorPrincipal) {

	}

	public void cadastraEleitor(Eleitor eleitor) {

	}

	public void removeEleitor(int titulo) {

	}

	public ArrayList<Eleitor> getEleitores() {
		return null;
	}

	public Eleitor getEleitorPeloTitulo(int titulo) {
		return null;
	}

	public void exibeTelaEleitor() {

	}

}
