package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaPrincipal;

/**
 *  
 *  @author 10349509913 
 * 
 */
public class ControladorPrincipal {

	public final ControladorCandidato controladorCandidato;

	public final ControladorEleitor controladorEleitor;

	public final ControladorUrna controladorUrna;

	public final ControladorCidade controladorCidade;

	public final ControladorPartido controladorPartido;
        
        public final TelaPrincipal telaPrincipal;
        
	

	public ControladorPrincipal() {
            
            controladorCandidato =  new ControladorCandidato(this);
            controladorCidade = new ControladorCidade(this);
            controladorEleitor = new ControladorEleitor(this);
            controladorPartido = new ControladorPartido(this);
            controladorUrna = new ControladorUrna(this);
            
            telaPrincipal = new TelaPrincipal(this);    
            telaPrincipal.setVisible(true);
	}
        
        

}
