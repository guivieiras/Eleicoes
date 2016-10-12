package br.ufsc.ine5605.trabalho1.controle;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaPrincipal;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.entidade.Urna;

/**
 *
 * @author 10349509913
 *
 */
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
        controladorEleitor.cadastra(new Eleitor(1, 1, 123456789888l, "Gui"));
        controladorPartido.cadastra(new Partido("Partido dos Anonimos Vagabundos", "PAV"));
        controladorPartido.cadastra(new Partido("Partido Verde", "PV"));
        controladorCandidato.cadastra(new Candidato(24, "Joao", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido dos Anonimos Vagabundos")));
        controladorCandidato.cadastra(new Candidato(25, "Zeee", Cargo.Prefeito, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido dos Anonimos Vagabundos")));
        controladorCandidato.cadastra(new Candidato(26, "Pedr", Cargo.Vereador, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido Verde")));
        controladorCandidato.cadastra(new Candidato(27, "Juca", Cargo.Prefeito, controladorCidade.getCidade("Florianópolis"), controladorPartido.getPartidoPorNome("Partido Verde")));
        controladorUrna.cadastra(new Urna(5, 1, 1, controladorCidade.getCidade("Florianópolis"), controladorCandidato.getLista(controladorCidade.getCidade("Florianópolis")), controladorPartido.getLista()));
       
        telaPrincipal = new TelaPrincipal(this);
        telaPrincipal.setVisible(true);
    }

}
