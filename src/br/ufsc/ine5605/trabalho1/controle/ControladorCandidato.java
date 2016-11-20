package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.*;
import br.ufsc.ine5605.trabalho1.entidade.*;
import br.ufsc.ine5605.trabalho1.exception.DoisPrefeitosPorPartidoException;
import br.ufsc.ine5605.trabalho1.mapeador.*;
import java.util.ArrayList;

public class ControladorCandidato implements IControlador<Candidato> {

    private static ControladorCandidato instance;
    private final Mapeador<Integer, Candidato> mapper;

    private ControladorCandidato() {
        this.mapper = new Mapeador<>("candidatos.urn");
        mapper.load();
    }

    public static ControladorCandidato getInstance() {
        if (instance == null) {
            instance = new ControladorCandidato();
        }
        return instance;
    }

    public void persist() {
        mapper.persist();
    }

    @Override
    public boolean cadastra(Candidato candidato) {
        if (testeCandidato(candidato, false)) {
            return mapper.put(candidato.getNumero(), candidato);
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Candidato candidato) {
        return mapper.remove(candidato.getNumero());
    }

    @Override
    public boolean modifica(Candidato antigo, Candidato novo) {
        if (testeCandidato(novo, true)) {
            mapper.remove(antigo.getNumero());

            antigo.setNome(novo.getNome());
            antigo.setNumero(novo.getNumero());
            antigo.setCargo(novo.getCargo());
            antigo.setCidade(novo.getCidade());
            antigo.setPartido(novo.getPartido());

            mapper.put(antigo.getNumero(), antigo);
            return true;
        }
        return false;
    }

    public boolean testeCandidato(Candidato candidato, boolean modificando) {
        for (Candidato candidatoCadastrado : mapper.getList()) {
            if (candidatoCadastrado.getNumero() == candidato.getNumero() && !modificando) {
                return false;
            }
            if (candidatoCadastrado.getCargo() == Cargo.Prefeito
                    && candidato.getCargo() == Cargo.Prefeito
                    && candidato.getPartido().equals(candidatoCadastrado.getPartido())
                    && candidato.getCidade().equals(candidatoCadastrado.getCidade())) {

                if (candidato.getNumero() != candidatoCadastrado.getNumero()) {
                    throw new DoisPrefeitosPorPartidoException();
                }
            }
        }
        if (candidato.getNumero() > 98 || candidato.getNumero() < 1) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Candidato> getLista() {
        return mapper.getList();
    }

    public ArrayList<Candidato> getLista(Cidade cidade) {
        ArrayList<Candidato> candidatosPorCidade = new ArrayList<>();

        for (Candidato candidato : mapper.getList()) {
            if (candidato.getCidade().equals(cidade)) {
                candidatosPorCidade.add(candidato);
            }
        }

        return candidatosPorCidade;
    }

    public Candidato getCandidato(int numero) {
        for (Candidato candidato : mapper.getList()) {
            if (candidato.getNumero() == numero) {
                return candidato;
            }
        }

        return null;
    }

    @Override
    public void exibeTela() {
        TelaCandidato tela = new TelaCandidato();
        tela.setVisible(true);

    }

}
