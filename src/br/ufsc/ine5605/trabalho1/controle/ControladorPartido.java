package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaPartido;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import java.util.ArrayList;

public class ControladorPartido implements IControlador<Partido> {

    public final ControladorPrincipal controladorPrincipal;
    private final ArrayList<Partido> partidos;

    public ControladorPartido(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        partidos = new ArrayList<>();
    }

    @Override
    public boolean cadastra(Partido partido) {
        for (Partido p : partidos)
            if (p.getNome().equals(partido.getNome()) || p.getSigla().equals(partido.getSigla()))
                return false;
        return partidos.add(partido);
    }

    @Override
    public boolean remove(Partido partido) {
        return partidos.remove(partido);
    }

    @Override
    public boolean modifica(Partido velho, Partido novo) {

        if (partidos.contains(velho)) {
            velho.setNome(novo.getNome());
            velho.setSigla(novo.getSigla());
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Partido> getLista() {
        return partidos;
    }
    
    public ArrayList<Partido> getLista(ArrayList<Candidato> candidatos)
    {
        ArrayList<Partido> partidos = new ArrayList<>();
        for (Candidato candidato : candidatos) {
            if (!partidos.contains(candidato.getPartido())) {
                partidos.add(candidato.getPartido());
            }
        }
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

    @Override
    public void exibeTela() {
        TelaPartido tela = new TelaPartido(this);
        tela.setVisible(true);
    }

}
