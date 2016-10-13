package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaEleitor;
import java.util.ArrayList;

public class ControladorEleitor implements IControlador<Eleitor> {

    public final ControladorPrincipal controladorPrincipal;
    private final ArrayList<Eleitor> eleitores;

    public ControladorEleitor(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.eleitores = new ArrayList<>();
    }

    @Override
    public boolean cadastra(Eleitor eleitor) {
        for (Eleitor eleitorCadastrado : eleitores) {
            if (eleitorCadastrado.getTitulo() == eleitor.getTitulo()) {
                return false;
            }
        }

        return eleitores.add(eleitor);     
    }

    @Override
    public boolean remove(Eleitor eleitor) {    
        return eleitores.remove(eleitor);
    }

    @Override
    public boolean modifica(Eleitor antigo, Eleitor novo) {
        if (eleitores.contains(antigo)) {
            antigo.setNome(novo.getNome());
            antigo.setTitulo(novo.getTitulo());
            antigo.setSecaoEleitoral(novo.getSecaoEleitoral());
            antigo.setZonaEleitoral(novo.getZonaEleitoral());
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Eleitor> getLista() {
        return eleitores;
    }

    public Eleitor getEleitor(long titulo) {
        for (Eleitor eleitor : eleitores) {
            if (eleitor.getTitulo() == titulo) {
                return eleitor;
            }
        }

        return null;
    }

    public Eleitor getEleitor(String nome) {
        for (Eleitor eleitor : eleitores) {
            if (eleitor.getNome().equals(nome)) {
                return eleitor;
            }
        }

        return null;
    }

    @Override
    public void exibeTela() {
        TelaEleitor tela = new TelaEleitor(this);
        tela.setVisible(true);

    }


}
