package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaPartidoOLD;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaPartido;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.mapeador.Mapeador;
import java.util.ArrayList;

public class ControladorPartido implements IControlador<Partido> {

    private static ControladorPartido instance;
    private final Mapeador<Integer, Partido> mapper;

    private ControladorPartido() {
        this.mapper = new Mapeador<>("partidos.urn");
        mapper.load();
    }

    public void persist() {
        mapper.persist();
    }

    @Override
    public boolean cadastra(Partido partido) {
        for (Partido partidoCadastrado : mapper.getList()) {
            if (partidoCadastrado.getNome().equals(partido.getNome())) {
                return false;
            }
        }
        return mapper.put(partido.getCodigo(), partido);
    }

    @Override
    public boolean remove(Partido partido) {
        return mapper.remove(partido.getCodigo());
    }

    @Override
    public boolean modifica(Partido velho, Partido novo) {

        if (mapper.contains(velho.getCodigo())) {
            velho.setNome(novo.getNome());
            velho.setSigla(novo.getSigla());
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Partido> getLista() {
        return mapper.getList();
    }

    public ArrayList<Partido> getLista(ArrayList<Candidato> candidatos) {
        ArrayList<Partido> partidos = new ArrayList<>();
        for (Candidato candidato : candidatos) {
            if (!partidos.contains(candidato.getPartido())) {
                partidos.add(candidato.getPartido());
            }
        }
        return partidos;
    }

    public Partido getPartidoPorNome(String nome) {
        for (Partido partido : mapper.getList()) {
            if (partido.getNome().equals(nome)) {
                return partido;
            }
        }
        return null;
    }

    public Partido getPartidoPorSigla(String sigla) {
        for (Partido partido : mapper.getList()) {
            if (partido.getSigla().equals(sigla)) {
                return partido;
            }
        }
        return null;
    }

    @Override
    public void exibeTela() {
        TelaPartido tela = new TelaPartido();
        tela.setVisible(true);
    }

    public static ControladorPartido getInstance() {
        if (instance == null) {
            instance = new ControladorPartido();
        }
        return instance;
    }

}
