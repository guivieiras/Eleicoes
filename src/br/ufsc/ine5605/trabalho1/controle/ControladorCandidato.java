package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaCandidato;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import java.util.ArrayList;

public class ControladorCandidato implements IControlador<Candidato> {

    public final ControladorPrincipal controladorPrincipal;
    private final ArrayList<Candidato> candidatos;

    public ControladorCandidato(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.candidatos = new ArrayList<>();
    }

    @Override
    public boolean cadastra(Candidato candidato) {
        for (Candidato candidatoCadastrado : candidatos) {
            if (candidatoCadastrado.getNumero() == candidato.getNumero()) {
                return false;
            }
        }
        if (candidato.getNumero() > 98 || candidato.getNumero() < 1)
            return false;

        return candidatos.add(candidato);

    }

    @Override
    public boolean remove(Candidato candidato) {
        return candidatos.remove(candidato);
    }

    @Override
    public boolean modifica(Candidato antigo, Candidato novo) {
        if (candidatos.contains(antigo)) {
            antigo.setNome(novo.getNome());
            antigo.setNumero(novo.getNumero());
            antigo.setCargo(novo.getCargo());
            antigo.setCidade(novo.getCidade());
            antigo.setPartido(novo.getPartido());
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Candidato> getLista() {
        return candidatos;
    }

    public ArrayList<Candidato> getLista(Cidade cidade) {
        ArrayList<Candidato> candidatosPorCidade = new ArrayList<>();

        for (Candidato candidato : candidatos) {
            if (candidato.getCidade().equals(cidade)) {
                candidatosPorCidade.add(candidato);
            }
        }

        return candidatosPorCidade;
    }
    
    public Candidato getCandidato(int codigo) {
        for (Candidato candidato : candidatos) {
            if (candidato.getNumero() == codigo) {
                return candidato;
            }
        }

        return null;
    }

    @Override
    public void exibeTela() {
        TelaCandidato tela = new TelaCandidato(this);
        tela.setVisible(true);

    }

}
