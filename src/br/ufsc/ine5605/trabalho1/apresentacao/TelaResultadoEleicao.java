/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.controle.ControladorCidade;
import br.ufsc.ine5605.trabalho1.controle.ControladorPrincipal;
import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.KeyValue;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Guilherme
 */
public class TelaResultadoEleicao extends JFrame {

    private JTextArea txtArea_Results;

    public TelaResultadoEleicao() {
        setTitle("Resultado eleições");
        setSize(950, 600);
        initComponents();
        imprimeResultado();
        txtArea_Results.setFont(new Font("Consolas", Font.PLAIN, 16));

    }

    public void imprimeResultado() {
        for (Cidade cidade : ControladorCidade.getInstance().getLista()) {
            Urna.Turno turno = Urna.Turno.Primeiro;
            for (Urna urna : ControladorUrna.getInstance().getLista()) {
                if (urna.getCidade().equals(cidade)) {
                    insereTexto(String.format("Seção: %1$d Zona: %2$d Cidade: %3$s\n", urna.getSecaoEleitoral(), urna.getZonaEleitoral(), urna.getCidade().getNome()));

                    if (urna.getTurno() == Urna.Turno.Primeiro) {
                        insereTexto("--------------- Vereadores ---------------\n");
                        LinkedHashMap<Candidato, Integer> vereadores = ControladorUrna.getInstance().ordenaHashMap(urna.getTotalDeVotosPorVereador());
                        for (Map.Entry<Candidato, Integer> entry : vereadores.entrySet()) {
                            insereTexto(entry.getKey().getNome() + "  (" + entry.getValue() + " votos)\n");
                        }
                        insereTexto(urna.getVotosInvalidosParaVerador() + " votos inválidos (" + urna.getVotosBrancosParaVereador() + " brancos e " + urna.getVotosNulosParaVereador() + " nulos)\n");
                    } else {
                        turno = Urna.Turno.Segundo;
                    }

                    insereTexto("--------------- Prefeitos  ---------------\n");
                    LinkedHashMap<Candidato, Integer> prefeitos = ControladorUrna.getInstance().ordenaHashMap(urna.getTotalDeVotosPorPrefeito());
                    for (Map.Entry<Candidato, Integer> entry : prefeitos.entrySet()) {
                        insereTexto(entry.getKey().getNome() + "  (" + entry.getValue() + " votos)\n");
                    }
                    insereTexto(urna.getVotosInvalidosParaPrefeito() + " votos inválidos (" + urna.getVotosBrancosParaPrefeito() + " brancos e " + urna.getVotosNulosParaPrefeito() + " nulos)\n");
                    //insereTexto("-------------- " + urna.getAbstencoes() + " abstenções --------------\n");
                    insereTexto("-----------------------------------------\n");

                    insereTexto("\n\n");
                }
            }
            insereTexto("--------- Vencedores " + cidade.getNome() + " --------\n");
            KeyValue<Candidato, Integer> prefeitoVotos = ControladorUrna.getInstance().prefeitoVencedor(cidade);
            insereTexto(String.format("Prefeito vencedor: %1$s (%2$d votos)\n", prefeitoVotos.key.getNome(), prefeitoVotos.value));

            if (turno == Urna.Turno.Primeiro) {
                insereTexto("Vereadores:\n");

                LinkedHashMap<Candidato, Integer> vereadoresVencedores = ControladorUrna.getInstance().vereadorVencedor(cidade);
                int vagas = 3;
                for (Map.Entry<Candidato, Integer> entry : vereadoresVencedores.entrySet()) {
                    if (vagas > -100) {
                        insereTexto(entry.getKey().getNome() + "  (" + entry.getValue() + " votos)\n");
                    }
                    vagas--;
                }
            }
            insereTexto("------------------------------------------\n");
            insereTexto("\n--------------------------------------------------------------------------------------------------\n\n");
        }
    }

    public void insereTexto(String txt) {
        txtArea_Results.append(txt);
    }

    private void initComponents() {
        getContentPane().setLayout(new GridBagLayout());
        txtArea_Results = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane(txtArea_Results);

        getContentPane().add(jScrollPane1, new GridBagConstraints(0, 0, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ControladorPrincipal.getInstance().liberaTelaPrincipal();
            }
        });
    }
}
