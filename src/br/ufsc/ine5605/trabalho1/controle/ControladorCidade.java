package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.apresentacao.TelaCidade;
import br.ufsc.ine5605.trabalho1.controle.ControladorPrincipal;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ControladorCidade implements IControlador<Cidade> {

    public final ControladorPrincipal controladorPrincipal;
    private ArrayList<Cidade> cidades;

    public ControladorCidade(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.cidades = new ArrayList<>();
    }

    @Override
    public boolean cadastra(Cidade cidade) {
        for (Cidade cidadeLista : cidades) {
            if (cidadeLista.getNome().equals(cidade.getNome())) {
                return false;
            }
        }

        cidades.add(cidade);
        return true;
    }

    @Override
    public boolean remove(Cidade cidade) {
        return cidades.remove(cidade);
    }

    @Override
    public boolean modifica(Cidade velha, Cidade nova) {
        if (cidades.contains(velha)) {
            velha.setNome(nova.getNome());
            return true;
        }
        return false;
    }

    public Cidade getCidade(String nome) {
        for (Cidade cidade : cidades) {
            if (cidade.getNome().equals(nome)) {
                return cidade;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Cidade> getLista() {
        return cidades;
    }

    @Override
    public void exibeTela() {
        TelaCidade tela = new TelaCidade(this);
        tela.setVisible(true);

    }

}
