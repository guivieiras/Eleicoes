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

	private ArrayList<Eleitor> eleitores;
        
	private TelaEleitor telaEleitor;

	public final ControladorPrincipal controladorPrincipal;

	public ControladorEleitor(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
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
            TelaEleitor te = new TelaEleitor(this);
            te.setVisible(true);
	}

}
