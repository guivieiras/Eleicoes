package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaPartido;
import java.util.ArrayList;

public class ControladorPartido implements IControlador<Partido> {

    public final ControladorPrincipal controladorPrincipal;
    private ArrayList<Partido> partidos;

    public ControladorPartido(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        partidos = new ArrayList<>();
    }

    public boolean cadastra(Partido partido) {
        for (Partido p : partidos)
            if (p.getNome().equals(partido.getNome()) || p.getSigla().equals(partido.getSigla()))
                return false;
        return partidos.add(partido);
    }

    public boolean remove(Partido partido) {
        return partidos.remove(partido);
    }

    public boolean modifica(Partido velho, Partido novo) {

        if (partidos.contains(velho)) {
            velho.setNome(novo.getNome());
            velho.setSigla(novo.getSigla());
            return true;
        }
        return false;
    }

    public ArrayList<Partido> getLista() {
        return partidos;
    }

    public Partido getPartidoPorNome(String nome) {
        for (Partido partido : partidos) {
            if (partido.getNome().equals(nome)) {
                return partido;
            }
        }
        return null;
    }
    public Partido getPartidoPorSigla(String sigla) {
        for (Partido partido : partidos) {
            if (partido.getSigla().equals(sigla)) {
                return partido;
            }
        }
        return null;
    }

    public void exibeTela() {
        TelaPartido tela = new TelaPartido(this);
        tela.setVisible(true);
    }

}
