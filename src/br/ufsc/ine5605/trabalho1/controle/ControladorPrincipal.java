package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaPrincipal;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.entidade.Urna;

public class ControladorPrincipal {

    public final ControladorCandidato controladorCandidato;
    public final ControladorEleitor controladorEleitor;
    public final ControladorUrna controladorUrna;
    public final ControladorCidade controladorCidade;
    public final ControladorPartido controladorPartido;
    public final TelaPrincipal telaPrincipal;

    public ControladorPrincipal() {

        controladorCandidato = new ControladorCandidato(this);
        controladorCidade = new ControladorCidade(this);
        controladorEleitor = new ControladorEleitor(this);
        controladorPartido = new ControladorPartido(this);
        controladorUrna = new ControladorUrna(this);

        controladorCidade.cadastra(new Cidade("Florianópolis"));
        controladorCidade.cadastra(new Cidade("São José"));
        controladorEleitor.cadastra(new Eleitor(1, 1, 123456789888l, "Gui", controladorCidade.getCidade("Florianópolis")));
        controladorEleitor.cadastra(new Eleitor(1, 1, 123456789887l, "Laa", controladorCidade.getCidade("Florianópolis")));
        controladorEleitor.cadastra(new Eleitor(1, 1, 123456789886l, "Lee", controladorCidade.getCidade("Florianópolis")));
        controladorPartido.cadastra(new Partido("Partido A", "PA"));
        controladorPartido.cadastra(new Partido("Partido B", "PB"));
        controladorCandidato.cadastra(new Candidato(20, "Joao", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(21, "Zeee", Cargo.Prefeito, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(22, "Pedr", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(23, "Juca", Cargo.Prefeito, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(24, "asda", Cargo.Prefeito, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(25, "gasd", Cargo.Prefeito, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(26, "bada", Cargo.Vereador, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(27, "weqe", Cargo.Vereador, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorUrna.cadastra(new Urna(5, 1, 1, controladorCidade.getCidade("Florianópolis"), controladorCandidato.getLista(controladorCidade.getCidade("Florianópolis"))));
        controladorUrna.cadastra(new Urna(5, 1, 1, controladorCidade.getCidade("São José"), controladorCandidato.getLista(controladorCidade.getCidade("São José"))));
       
        telaPrincipal = new TelaPrincipal(this);
        telaPrincipal.setVisible(true);
    }

}
