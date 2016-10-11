package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaUrna;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import java.util.ArrayList;

public class ControladorUrna {

    public final ControladorPrincipal controladorPrincipal;
    private ArrayList<Urna> urnas;

    public ControladorUrna(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
            this.urnas = new ArrayList<>();
    }
    public boolean cadastraUrna(Urna novaUrna){
        for(Urna urna: this.urnas){
            if(!(novaUrna.getCidade()==urna.getCidade()&&novaUrna.getZonaEleitoral()==urna.getZonaEleitoral()&&novaUrna.getSecaoEleitoral()==urna.getSecaoEleitoral())){
                urnas.add(urna);
    		return true;
            }
        }
        return false;
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