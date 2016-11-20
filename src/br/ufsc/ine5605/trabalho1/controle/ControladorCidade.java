package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.*;
import br.ufsc.ine5605.trabalho1.apresentacao.*;
import br.ufsc.ine5605.trabalho1.mapeador.*;
import java.util.ArrayList;

public class ControladorCidade implements IControlador<Cidade> {

    private static ControladorCidade instance;
    private final Mapeador<Integer, Cidade> mapper;

    private ControladorCidade() {
        this.mapper = new Mapeador<>("cidades.urn");
        mapper.load();

    }

    public void persist() {
        mapper.persist();
    }

    public static ControladorCidade getInstance() {
        if (instance == null) {
            instance = new ControladorCidade();
        }
        return instance;
    }

    @Override
    public boolean cadastra(Cidade cidade) {
        for (Cidade cidadeLista : mapper.getList()) {
            if (cidadeLista.getNome().equals(cidade.getNome())) {
                return false;
            }
        }

        mapper.put(cidade.getCodigo(), cidade);
        return true;
    }

    @Override
    public boolean remove(Cidade cidade) {
        return mapper.remove(cidade.getCodigo());
    }

    @Override
    public boolean modifica(Cidade velha, Cidade nova) {
        if (mapper.contains(velha.getCodigo())) {
            velha.setNome(nova.getNome());
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Cidade> getLista() {
        return mapper.getList();
    }

    public Cidade getCidade(String nome) {
        for (Cidade cidade : mapper.getList()) {
            if (cidade.getNome().equals(nome)) {
                return cidade;
            }
        }

        return null;
    }

    @Override
    public void exibeTela() {
        TelaCidade tela = new TelaCidade();
        tela.setVisible(true);

    }

}
