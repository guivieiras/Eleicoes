package br.ufsc.ine5605.trabalho1.apresentacao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ufsc.ine5605.trabalho1.controle.*;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author The Gui
 */
public class TelaPrincipal extends JFrame {

    private final ActionManager actionManager = new ActionManager();

    private JPanel jpanel;
    private JButton btn_IniciarEleicoes;
    private JButton btn_TelaCandidato;
    private JButton btn_TelaCidade;
    private JButton btn_TelaEleitor;
    private JButton btn_TelaPartido;
    private JButton btn_TelaResultados;
    private JButton btn_TelaUrna;
    private JButton btn_ResetEleicoes;

    public TelaPrincipal() {
        setTitle("Menu principal");
        setSize(230, 400);
        initComponents();
        initButtonAction();

    }

    private void initComponents() {
        jpanel = new JPanel();
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
        getContentPane().add(jpanel);

        btn_IniciarEleicoes = new JButton("Iniciar eleições");
        btn_TelaCidade = new JButton("Cidades");
        btn_TelaCandidato = new JButton("Candidatos");
        btn_TelaEleitor = new JButton("Eleitores");
        btn_TelaPartido = new JButton("Partidos");
        btn_TelaResultados = new JButton("Resultado da eleição");
        btn_TelaUrna = new JButton("Urnas");
        btn_ResetEleicoes = new JButton("Reset eleições");

        btn_TelaResultados.setEnabled(false);

        btn_IniciarEleicoes.setMaximumSize(new Dimension(180, 200));
        btn_TelaCandidato.setMaximumSize(new Dimension(180, 200));
        btn_TelaCidade.setMaximumSize(new Dimension(180, 200));
        btn_TelaEleitor.setMaximumSize(new Dimension(180, 200));
        btn_TelaPartido.setMaximumSize(new Dimension(180, 200));
        btn_TelaResultados.setMaximumSize(new Dimension(180, 200));
        btn_TelaUrna.setMaximumSize(new Dimension(180, 200));
        btn_ResetEleicoes.setMaximumSize(new Dimension(180, 200));

        btn_IniciarEleicoes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_TelaCidade.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_TelaCandidato.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_TelaEleitor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_TelaPartido.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_TelaResultados.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_TelaUrna.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_ResetEleicoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_TelaCidade);
        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_TelaEleitor);
        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_TelaPartido);
        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_TelaCandidato);
        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_TelaUrna);
        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_IniciarEleicoes);
        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_TelaResultados);
        jpanel.add(Box.createVerticalGlue());
        jpanel.add(btn_ResetEleicoes);
        jpanel.add(Box.createVerticalGlue());

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initButtonAction() {
        btn_IniciarEleicoes.addActionListener(actionManager);
        btn_TelaCidade.addActionListener(actionManager);
        btn_TelaResultados.addActionListener(actionManager);
        btn_TelaUrna.addActionListener(actionManager);
        btn_TelaCandidato.addActionListener(actionManager);
        btn_TelaPartido.addActionListener(actionManager);
        btn_TelaEleitor.addActionListener(actionManager);
        btn_ResetEleicoes.addActionListener(actionManager);

        btn_IniciarEleicoes.setActionCommand(btn_IniciarEleicoes.getText());
        btn_TelaCidade.setActionCommand(btn_TelaCidade.getText());
        btn_TelaResultados.setActionCommand(btn_TelaResultados.getText());
        btn_TelaUrna.setActionCommand(btn_TelaUrna.getText());
        btn_TelaCandidato.setActionCommand(btn_TelaCandidato.getText());
        btn_TelaPartido.setActionCommand(btn_TelaPartido.getText());
        btn_TelaEleitor.setActionCommand(btn_TelaEleitor.getText());
        btn_ResetEleicoes.setActionCommand(btn_ResetEleicoes.getText());
    }

    public void blockButtons(boolean eleicaoEncerrada) {

        btn_TelaCandidato.setEnabled(false);
        btn_TelaEleitor.setEnabled(false);
        btn_TelaCidade.setEnabled(false);
        btn_TelaPartido.setEnabled(false);
        btn_TelaUrna.setEnabled(false);
        btn_IniciarEleicoes.setEnabled(false);

        btn_TelaResultados.setEnabled(eleicaoEncerrada);
    }

    public void liberaButtons() {
        btn_TelaCandidato.setEnabled(true);
        btn_TelaEleitor.setEnabled(true);
        btn_TelaCidade.setEnabled(true);
        btn_TelaPartido.setEnabled(true);
        btn_TelaUrna.setEnabled(true);
        btn_IniciarEleicoes.setEnabled(true);
        btn_TelaResultados.setEnabled(false);
    }

    class ActionManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btn_IniciarEleicoes.getText())) {

                for (Cidade cidade : ControladorCidade.getInstance().getLista()) {
                    if (ControladorUrna.getInstance().getLista(cidade).isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há como iniciar as eleições, não há urnas cadastradas na cidade: " + cidade.getNome(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }

                blockButtons(false);
                setEnabled(false);
                ControladorUrna.getInstance().iniciaEleicoes();
            }
            if (e.getActionCommand().equals(btn_TelaCandidato.getText())) {
                setEnabled(false);
                ControladorCandidato.getInstance().exibeTela();
            }
            if (e.getActionCommand().equals(btn_TelaCidade.getText())) {
                setEnabled(false);
                ControladorCidade.getInstance().exibeTela();
            }
            if (e.getActionCommand().equals(btn_TelaEleitor.getText())) {
                setEnabled(false);
                ControladorEleitor.getInstance().exibeTela();
            }
            if (e.getActionCommand().equals(btn_TelaPartido.getText())) {
                setEnabled(false);
                ControladorPartido.getInstance().exibeTela();
            }
            if (e.getActionCommand().equals(btn_TelaResultados.getText())) {
            //    setEnabled(false);
                ControladorUrna.getInstance().exibeTelaResultado();
            }
            if (e.getActionCommand().equals(btn_TelaUrna.getText())) {
                setEnabled(false);
                ControladorUrna.getInstance().exibeTela();
            }
            if (e.getActionCommand().equals(btn_ResetEleicoes.getText())) {
                if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja resetar a eleição? Esta ação ira zerar todos os votos já registrados nas urnas e alterar o estado das urnas para 'Aberto'", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    ControladorUrna.getInstance().resetEleicao();
                }
            }

        }
    }
}
