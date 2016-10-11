package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaUrna;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import java.util.ArrayList;

public class ControladorUrna {

    public final ControladorPrincipal controladorPrincipal;
    private ArrayList<Urna> urnas;

    public ControladorUrna(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
    }

    public boolean cadastraUrna(int numeroEleitores, int secaoEleitoral, int zonaEleitoral, Cidade cidade, ArrayList<Candidato> candidatos){
    	try{
    		urnas.add(new Urna(numeroEleitores, secaoEleitoral, zonaEleitoral, cidade, candidatos));
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }
    public void removeUrna(Urna urna) {
    	urnas.remove(urna);
    }

    public ArrayList<Urna> getUrnas() {
        return urnas;
    }

    public void exibeTelaUrna() {
    	TelaUrna tela = new TelaUrna(this);
    	tela.setVisible(true);
    }

 
}
