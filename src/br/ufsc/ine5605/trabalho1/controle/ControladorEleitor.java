package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaEleitor;
import java.util.ArrayList;

/**
 *  
 *  @author 10349509913 
 * 
 */
public class ControladorEleitor {

        public final ControladorPrincipal controladorPrincipal;
        private ArrayList<Eleitor> eleitores;    

	public ControladorEleitor(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
	}

	public void cadastraEleitor(Eleitor eleitor) {

	}

	public void removeEleitor(int titulo) {

	}
        public void modificaEleitor(Eleitor eleitor, int titulo, String nome){
            
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
