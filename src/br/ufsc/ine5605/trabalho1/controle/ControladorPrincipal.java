package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaPrincipal;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.entidade.Urna;

public class ControladorPrincipal {

 
    public final TelaPrincipal telaPrincipal;

    private static ControladorPrincipal instance;
    

    protected ControladorPrincipal() {

   
        //inicializaVariaveis();
        //votacaoTeste();
        
        ControladorCandidato.getInstance();
        ControladorPartido.getInstance();
        ControladorCidade.getInstance();
        ControladorEleitor.getInstance();
        ControladorUrna.getInstance();
        
        telaPrincipal = new TelaPrincipal(this);
        telaPrincipal.setVisible(true);
    }

    public static ControladorPrincipal getInstance() {
        if (instance == null) {
            instance = new ControladorPrincipal();
        }
        return instance;

    }

    public void inicializaVariaveis() {
        ControladorCidade.getInstance().cadastra(new Cidade("Florianópolis"));
        ControladorCidade.getInstance().cadastra(new Cidade("São José"));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 1, 1111111111110l, "A", ControladorCidade.getInstance().getCidade("Florianópolis")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 1, 111111111111l, "B", ControladorCidade.getInstance().getCidade("Florianópolis")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 2, 111111111112l, "C", ControladorCidade.getInstance().getCidade("Florianópolis")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 2, 111111111113l, "D", ControladorCidade.getInstance().getCidade("Florianópolis")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 2, 111111111114l, "E", ControladorCidade.getInstance().getCidade("Florianópolis")));

        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 2, 111111111115l, "A", ControladorCidade.getInstance().getCidade("São José")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 2, 111111111116l, "B", ControladorCidade.getInstance().getCidade("São José")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 1, 111111111117l, "C", ControladorCidade.getInstance().getCidade("São José")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 1, 111111111118l, "D", ControladorCidade.getInstance().getCidade("São José")));
        ControladorEleitor.getInstance().cadastra(new Eleitor(1, 2, 111111111119l, "E", ControladorCidade.getInstance().getCidade("São José")));

        ControladorPartido.getInstance().cadastra(new Partido("Partido A", "PA"));
        ControladorPartido.getInstance().cadastra(new Partido("Partido B", "PB"));

        ControladorCandidato.getInstance().cadastra(new Candidato(20, "Candidato A", Cargo.Vereador, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorPartido.getInstance().getPartidoPorNome("Partido A")));
        ControladorCandidato.getInstance().cadastra(new Candidato(21, "Candidato B", Cargo.Vereador, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorPartido.getInstance().getPartidoPorNome("Partido A")));
        ControladorCandidato.getInstance().cadastra(new Candidato(22, "Candidato C", Cargo.Vereador, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorPartido.getInstance().getPartidoPorNome("Partido B")));
        ControladorCandidato.getInstance().cadastra(new Candidato(23, "Candidato D", Cargo.Vereador, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorPartido.getInstance().getPartidoPorNome("Partido B")));
        ControladorCandidato.getInstance().cadastra(new Candidato(24, "Candidato E", Cargo.Vereador, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorPartido.getInstance().getPartidoPorNome("Partido B")));

        ControladorCandidato.getInstance().cadastra(new Candidato(30, "Candidato F", Cargo.Prefeito, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorPartido.getInstance().getPartidoPorNome("Partido A")));
        ControladorCandidato.getInstance().cadastra(new Candidato(31, "Candidato G", Cargo.Prefeito, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorPartido.getInstance().getPartidoPorNome("Partido B")));

        ControladorCandidato.getInstance().cadastra(new Candidato(41, "Candidato H", Cargo.Vereador, ControladorCidade.getInstance().getCidade("São José"), ControladorPartido.getInstance().getPartidoPorNome("Partido A")));
        ControladorCandidato.getInstance().cadastra(new Candidato(40, "Candidato I", Cargo.Vereador, ControladorCidade.getInstance().getCidade("São José"), ControladorPartido.getInstance().getPartidoPorNome("Partido A")));
        ControladorCandidato.getInstance().cadastra(new Candidato(42, "Candidato J", Cargo.Vereador, ControladorCidade.getInstance().getCidade("São José"), ControladorPartido.getInstance().getPartidoPorNome("Partido B")));
        ControladorCandidato.getInstance().cadastra(new Candidato(43, "Candidato K", Cargo.Vereador, ControladorCidade.getInstance().getCidade("São José"), ControladorPartido.getInstance().getPartidoPorNome("Partido B")));
        ControladorCandidato.getInstance().cadastra(new Candidato(44, "Candidato L", Cargo.Vereador, ControladorCidade.getInstance().getCidade("São José"), ControladorPartido.getInstance().getPartidoPorNome("Partido A")));

        ControladorCandidato.getInstance().cadastra(new Candidato(51, "Candidato M", Cargo.Prefeito, ControladorCidade.getInstance().getCidade("São José"), ControladorPartido.getInstance().getPartidoPorNome("Partido B")));
        ControladorCandidato.getInstance().cadastra(new Candidato(50, "Candidato N", Cargo.Prefeito, ControladorCidade.getInstance().getCidade("São José"), ControladorPartido.getInstance().getPartidoPorNome("Partido A")));

        ControladorUrna.getInstance().cadastra(new Urna(5, 1, 1, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorCandidato.getInstance().getLista(ControladorCidade.getInstance().getCidade("Florianópolis")), Urna.Turno.Primeiro));
        ControladorUrna.getInstance().cadastra(new Urna(5, 2, 1, ControladorCidade.getInstance().getCidade("Florianópolis"), ControladorCandidato.getInstance().getLista(ControladorCidade.getInstance().getCidade("Florianópolis")), Urna.Turno.Primeiro));

        ControladorUrna.getInstance().cadastra(new Urna(5, 1, 1, ControladorCidade.getInstance().getCidade("São José"), ControladorCandidato.getInstance().getLista(ControladorCidade.getInstance().getCidade("São José")), Urna.Turno.Primeiro));
        ControladorUrna.getInstance().cadastra(new Urna(5, 2, 1, ControladorCidade.getInstance().getCidade("São José"), ControladorCandidato.getInstance().getLista(ControladorCidade.getInstance().getCidade("São José")), Urna.Turno.Primeiro));
    }

    //Metodo para simular uma votação automaticamente, sem nenhum tipo de verificação
    public void votacaoTeste() {
        ControladorUrna.getInstance().getLista().get(0).contabilizaVoto(30, 20);
        ControladorUrna.getInstance().getLista().get(0).contabilizaVoto(30, 21);
        ControladorUrna.getInstance().getLista().get(0).contabilizaVoto(31, 22);

        ControladorUrna.getInstance().getLista().get(1).contabilizaVoto(31, 24);
        ControladorUrna.getInstance().getLista().get(1).contabilizaVoto(31, 24);

        ControladorUrna.getInstance().getLista().get(2).contabilizaVoto(50, 40);
        ControladorUrna.getInstance().getLista().get(2).contabilizaVoto(51, 42);
        ControladorUrna.getInstance().getLista().get(2).contabilizaVoto(51, 44);

        ControladorUrna.getInstance().getLista().get(3).contabilizaVoto(51, 42);
        ControladorUrna.getInstance().getLista().get(3).contabilizaVoto(50, 41);

    }

}
