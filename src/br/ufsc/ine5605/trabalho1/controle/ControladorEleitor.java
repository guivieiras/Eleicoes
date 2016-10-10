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
        private  ArrayList<Eleitor> eleitores;    

	public ControladorEleitor(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
            this.eleitores = new ArrayList<>();
	}

	public void cadastraEleitor(Eleitor eleitor) {
            eleitores.add(eleitor);
	}

	public void removeEleitor(long titulo) {

	}
        public void modificaEleitor(Eleitor eleitor, long titulo, String nome){
            
        }

	public ArrayList<Eleitor> getEleitores() {
		return eleitores;
	}

	public Eleitor getEleitorPeloTitulo(long titulo) {
		return null;
	}

	public void exibeTelaEleitor() {
            TelaEleitor te = new TelaEleitor(this);
            te.setVisible(true);
	}

    public Eleitor getEleitor(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Eleitor getEleitor(long parseLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
