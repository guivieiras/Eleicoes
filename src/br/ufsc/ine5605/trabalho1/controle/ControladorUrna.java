package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaUrna;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import java.util.ArrayList;
import java.util.HashSet;

public class ControladorUrna implements IControlador<Urna> {

    public final ControladorPrincipal controladorPrincipal;
    private ArrayList<Urna> urnas;

    public ControladorUrna(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.urnas = new ArrayList<>();
    }

    public boolean cadastra(Urna novaUrna) {
        for (Urna urna : urnas) {
            if (novaUrna.getCidade() == urna.getCidade()
                    && novaUrna.getZonaEleitoral() == urna.getZonaEleitoral()
                    && novaUrna.getSecaoEleitoral() == urna.getSecaoEleitoral()) {
                return false;
            }
        }
        return urnas.add(novaUrna);
    }

    public boolean cadastra(Cidade cidade, int secaoEleitoral, int zonaEleitoral, int limiteDeEleitores) {
        for (Urna urna : urnas) {
            if (cidade == urna.getCidade()
                    && zonaEleitoral == urna.getZonaEleitoral()
                    && secaoEleitoral == urna.getSecaoEleitoral()) {
                return false;
            }
        }

        ArrayList<Candidato> candidatos = controladorPrincipal.controladorCandidato.getLista(cidade);
        ArrayList<Partido> partidos = new ArrayList<>();

        for (Candidato candidato : candidatos) {
            if (!partidos.contains(candidato.getPartido())) {
                partidos.add(candidato.getPartido());
            }
        }

        Urna urna = new Urna(limiteDeEleitores, secaoEleitoral, zonaEleitoral, cidade, candidatos, partidos);
        return urnas.add(urna);
    }

    public boolean remove(Urna urna) {
        return urnas.remove(urna);
    }

    public boolean modifica(Urna nova, Urna antiga) {
        return false;
    }

    public ArrayList<Urna> getLista() {
        return urnas;
    }

    public void exibeTela() {
        TelaUrna tela = new TelaUrna(this);
        tela.setVisible(true);
    }
}
