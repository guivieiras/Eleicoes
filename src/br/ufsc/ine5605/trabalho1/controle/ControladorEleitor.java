package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaEleitorOLD;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaEleitor;
import br.ufsc.ine5605.trabalho1.mapeador.Mapeador;
import java.util.ArrayList;

public class ControladorEleitor implements IControlador<Eleitor> {

    private static ControladorEleitor instance;
    private final Mapeador<Long, Eleitor> mapper;

    private ControladorEleitor() {
        this.mapper = new Mapeador<>("eleitores.urn");
        mapper.load();
    }

    public void persist() {
        mapper.persist();
    }

    public static ControladorEleitor getInstance() {
        if (instance == null) {
            instance = new ControladorEleitor();
        }

        return instance;
    }

    @Override
    public boolean cadastra(Eleitor eleitor) {
        for (Eleitor eleitorCadastrado : mapper.getList()) {
            if (eleitorCadastrado.getTitulo() == eleitor.getTitulo()) {
                return false;
            }
        }

        return mapper.put(eleitor.getTitulo(), eleitor);
    }

    @Override
    public boolean remove(Eleitor eleitor) {
        return mapper.remove(eleitor.getTitulo());
    }

    @Override
    public boolean modifica(Eleitor antigo, Eleitor novo) {
        if (mapper.contains(antigo.getTitulo())) {
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
        return mapper.getList();
    }

    public Eleitor getEleitor(long titulo) {
        for (Eleitor eleitor : mapper.getList()) {
            if (eleitor.getTitulo() == titulo) {
                return eleitor;
            }
        }

        return null;
    }

    public Eleitor getEleitor(String nome) {
        for (Eleitor eleitor : mapper.getList()) {
            if (eleitor.getNome().equals(nome)) {
                return eleitor;
            }
        }

        return null;
    }

    @Override
    public void exibeTela() {
        TelaEleitor tela = new TelaEleitor();
        tela.setVisible(true);

    }

}
