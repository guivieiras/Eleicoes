package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaPrincipal;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaUrna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaVotacao;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import java.util.ArrayList;

public class ControladorUrna {

    public final ControladorPrincipal controladorPrincipal;
    private ArrayList<Urna> urnas;

    public ControladorUrna(ControladorPrincipal controladorPrincipal) {
            this.controladorPrincipal = controladorPrincipal;
    }

    public void cadastraUrna(Cidade cidade, int limiteDeVotos, int zonaEleitoral, int secaoEleitoral) {

    }

    public void removeUrna(Urna urna) {

    }

    public ArrayList<Urna> getUrnas() {
        return null;
    }

    public void exibeTelaUrna() {

    }
}
