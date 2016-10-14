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

        //inicializaVariaveis();

        telaPrincipal = new TelaPrincipal(this);
        telaPrincipal.setVisible(true);
    }
    
    public void inicializaVariaveis()
    {
        controladorCidade.cadastra(new Cidade("Florianópolis"));
        controladorCidade.cadastra(new Cidade("São José"));
        controladorEleitor.cadastra(new Eleitor(1, 1, 1111111111110l, "A", controladorCidade.getCidade("Florianópolis")));
        controladorEleitor.cadastra(new Eleitor(1, 1, 111111111111l, "B", controladorCidade.getCidade("Florianópolis")));
        controladorEleitor.cadastra(new Eleitor(1, 2, 111111111112l, "C", controladorCidade.getCidade("Florianópolis")));
        controladorEleitor.cadastra(new Eleitor(1, 2, 111111111113l, "D", controladorCidade.getCidade("Florianópolis")));
        controladorEleitor.cadastra(new Eleitor(1, 2, 111111111114l, "E", controladorCidade.getCidade("Florianópolis")));
        
        controladorEleitor.cadastra(new Eleitor(1, 2, 111111111115l, "A", controladorCidade.getCidade("São José")));
        controladorEleitor.cadastra(new Eleitor(1, 2, 111111111116l, "B", controladorCidade.getCidade("São José")));
        controladorEleitor.cadastra(new Eleitor(1, 1, 111111111117l, "C", controladorCidade.getCidade("São José")));
        controladorEleitor.cadastra(new Eleitor(1, 1, 111111111118l, "D", controladorCidade.getCidade("São José")));
        controladorEleitor.cadastra(new Eleitor(1, 2, 111111111119l, "E", controladorCidade.getCidade("São José")));
        
        
        controladorPartido.cadastra(new Partido("Partido A", "PA"));
        controladorPartido.cadastra(new Partido("Partido B", "PB"));

        controladorCandidato.cadastra(new Candidato(20, "Candidato A", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(21, "Candidato B", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(22, "Candidato C", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(23, "Candidato D", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(24, "Candidato E", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido B")));

        controladorCandidato.cadastra(new Candidato(30, "Candidato F", Cargo.Prefeito, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(31, "Candidato G", Cargo.Prefeito, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido B")));

        controladorCandidato.cadastra(new Candidato(41, "Candidato H", Cargo.Vereador, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(40, "Candidato I", Cargo.Vereador, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido A")));
        controladorCandidato.cadastra(new Candidato(42, "Candidato J", Cargo.Vereador, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(43, "Candidato K", Cargo.Vereador, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(44, "Candidato L", Cargo.Vereador, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido A")));

        controladorCandidato.cadastra(new Candidato(51, "Candidato M", Cargo.Prefeito, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido B")));
        controladorCandidato.cadastra(new Candidato(50, "Candidato N", Cargo.Prefeito, controladorCidade.getCidade("São José"), controladorPartido.getPartidoPorNome("Partido A")));

        controladorUrna.cadastra(new Urna(5, 1, 1, controladorCidade.getCidade("Florianópolis"), controladorCandidato.getLista(controladorCidade.getCidade("Florianópolis")), Urna.Turno.Primeiro));
        controladorUrna.cadastra(new Urna(5, 2, 1, controladorCidade.getCidade("Florianópolis"), controladorCandidato.getLista(controladorCidade.getCidade("Florianópolis")), Urna.Turno.Primeiro));

        controladorUrna.cadastra(new Urna(5, 1, 1, controladorCidade.getCidade("São José"), controladorCandidato.getLista(controladorCidade.getCidade("São José")), Urna.Turno.Primeiro));
        controladorUrna.cadastra(new Urna(5, 2, 1, controladorCidade.getCidade("São José"), controladorCandidato.getLista(controladorCidade.getCidade("São José")), Urna.Turno.Primeiro));
    }

}
