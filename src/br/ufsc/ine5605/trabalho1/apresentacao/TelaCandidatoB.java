/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.constantes.Actions;

import br.ufsc.ine5605.trabalho1.controle.*;
import br.ufsc.ine5605.trabalho1.entidade.*;
import br.ufsc.ine5605.trabalho1.exception.NomeVazio;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author The Gui
 */
public class TelaCandidatoB extends Tela<Candidato> {

    private JTabbedPane jTabbedPane;
    private ActionManager actionManager = new ActionManager();
    private Candidato candidatoModificado;

    //Panel lista
    private JPanel panel_Lista;
    private JTable jtable;

    //Panel cadastro
    private JPanel panel_Cadastro;
    private JButton btn_Cadastro;
    private JTextField txt_Nome;
    private JTextField txt_Numero;
    private JComboBox<Cargo> cBox_Cargo;
    private JComboBox<Cidade> cBox_Cidade;
    private JComboBox<Partido> cBox_Partido;
    //panel modifica

    private JPanel panel_Modifica;
    private JButton btn_Modificar;
    private JButton btn_ProcuraPorNumero;
    private JButton btn_Remove;
    private JTextField txt_ModificaNome;
    private JTextField txt_ModificaNumero;
    private JComboBox<String> cBox_ModificaCargo;
    private JComboBox<String> cBox_ModificaCidade;
    private JComboBox<String> cBox_ModificaPartido;
    //panel lista

    public TelaCandidatoB() {
        setTitle("Candidatos");
        initComponents();
    }

    private void initComponents() {

        jTabbedPane = new JTabbedPane();
        getContentPane().add(jTabbedPane);
        setSize(450, 350);

        //Painel lista
        jtable = new JTable();
        panel_Lista = new JPanel();

        JScrollPane tableContainer = new JScrollPane(jtable);

        jtable.setModel(new javax.swing.table.DefaultTableModel(
                new String[][]{}, new String[]{
                    "Nome", "Numero", "Partido", "Cargo", "Cidade"
                }
        ) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });

        panel_Lista.add(tableContainer);
        jTabbedPane.add("Lista", panel_Lista);

        //Painel Cadastro
        panel_Cadastro = new JPanel();
        btn_Cadastro = new JButton("Cadastrar");

        txt_Nome = new JTextField();
        txt_Numero = new JTextField();

        cBox_Cidade = new JComboBox<>();
        cBox_Partido = new JComboBox<>();
        cBox_Cargo = new JComboBox<>();

        panel_Cadastro.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        //   c.anchor = GridBagConstraints.NORTHWEST;

        c1.insets = new Insets(40, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 0;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Nome"), c1);

        c1.insets = new Insets(40, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 0;
        c1.weightx = 1;
        panel_Cadastro.add(txt_Nome, c1);

        c1.insets = new Insets(15, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 1;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Número"), c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 1;
        c1.weightx = 1;
        panel_Cadastro.add(txt_Numero, c1);

        c1.insets = new Insets(15, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 2;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Cargo"), c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 2;
        c1.weightx = 1;
        panel_Cadastro.add(cBox_Cargo, c1);

        c1.insets = new Insets(15, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 3;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Cidade"), c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 3;
        c1.weightx = 1;
        panel_Cadastro.add(cBox_Cidade, c1);

        c1.insets = new Insets(15, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 4;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Partido"), c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 4;
        c1.weightx = 1;
        panel_Cadastro.add(cBox_Partido, c1);

        c1.insets = new Insets(27, 10, 20, 15);
        c1.gridx = 2;
        c1.gridy = 5;
        c1.weightx = .01;
        c1.weighty = 1;
        panel_Cadastro.add(btn_Cadastro, c1);

        jTabbedPane.add("Cadastro", panel_Cadastro);

        //Panel Modifica
        panel_Modifica = new JPanel();

        btn_Modificar = new JButton("Modificar");
        btn_ProcuraPorNumero = new JButton("Pesquisar");
        btn_Remove = new JButton("Remover");

        txt_ModificaNome = new JTextField();
        txt_ModificaNumero = new JTextField();

        cBox_ModificaCidade = new JComboBox<>();
        cBox_ModificaPartido = new JComboBox<>();
        cBox_ModificaCargo = new JComboBox<>();

        panel_Modifica.setLayout(new GridBagLayout());

        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;

        c2.insets = new Insets(40, 60, 0, 0);
        c2.gridx = 0;
        c2.gridy = 0;
        c2.weightx = 0;
        panel_Modifica.add(new JLabel("Nome"), c2);

        c2.insets = new Insets(40, 40, 0, 0);
        c2.gridx = 1;
        c2.gridy = 0;
        c2.weightx = 1;
        panel_Modifica.add(txt_ModificaNome, c2);

        c2.insets = new Insets(15, 60, 0, 0);
        c2.gridx = 0;
        c2.gridy = 1;
        c2.weightx = 0;
        panel_Modifica.add(new JLabel("Número"), c2);

        c2.insets = new Insets(15, 40, 0, 0);
        c2.gridx = 1;
        c2.gridy = 1;
        c2.weightx = 1;
        panel_Modifica.add(txt_ModificaNumero, c2);

        c2.insets = new Insets(15, 20, 0, 20);
        c2.gridx = 2;
        c2.gridy = 1;
        c2.weightx = 0;
        panel_Modifica.add(btn_ProcuraPorNumero, c2);

        c2.insets = new Insets(15, 60, 0, 0);
        c2.gridx = 0;
        c2.gridy = 2;
        c2.weightx = 0;
        panel_Modifica.add(new JLabel("Cargo"), c2);

        c2.insets = new Insets(15, 40, 0, 0);
        c2.gridx = 1;
        c2.gridy = 2;
        c2.weightx = 1;
        panel_Modifica.add(cBox_ModificaCargo, c2);

        c2.insets = new Insets(15, 60, 0, 0);
        c2.gridx = 0;
        c2.gridy = 3;
        c2.weightx = 0;
        panel_Modifica.add(new JLabel("Cidade"), c2);

        c2.insets = new Insets(15, 40, 0, 0);
        c2.gridx = 1;
        c2.gridy = 3;
        c2.weightx = 1;
        panel_Modifica.add(cBox_ModificaCidade, c2);

        c2.insets = new Insets(15, 60, 0, 0);
        c2.gridx = 0;
        c2.gridy = 4;
        c2.weightx = 0;
        panel_Modifica.add(new JLabel("Partido"), c2);

        c2.insets = new Insets(15, 40, 0, 0);
        c2.gridx = 1;
        c2.gridy = 4;
        c2.weightx = 1;
        panel_Modifica.add(cBox_ModificaPartido, c2);

        c2.insets = new Insets(27, 100, 20, 15);
        c2.gridx = 1;
        c2.gridy = 5;
        c2.weightx = 0;
        c2.fill = GridBagConstraints.NONE;
        c2.weighty = 1;
        panel_Modifica.add(btn_Remove, c2);

        c2.insets = new Insets(27, 5, 20, 15);
        c2.gridx = 2;
        c2.gridy = 5;
        c2.weightx = 0;
        c2.weighty = 1;
        panel_Modifica.add(btn_Modificar, c2);

        jTabbedPane.add("Modificar", panel_Modifica);
        //

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setButtonActions() {
        btn_Cadastro.addActionListener(actionManager);

    }

    @Override
    Object[] atributosParaArray(Candidato candidato) {
        return new Object[]{candidato.getNome(), candidato.getNumero(), candidato.getPartido().getNome(), candidato.getCargo().toString(), candidato.getCidade().getNome()};
    }

    class ActionManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(Actions.CADASTRAR)) {
                if (verificaNumero(txt_Numero.getText())) {
                    try {
                        Cargo cargo = Cargo.valueOf(cBox_Cargo.getSelectedItem().toString());
                        Partido partido = ControladorPartido.getInstance().getPartidoPorNome(cBox_Partido.getSelectedItem().toString());
                        Cidade cidade = ControladorCidade.getInstance().getCidade(cBox_Cidade.getSelectedItem().toString());

                        Candidato candidato = new Candidato(Integer.parseInt(txt_Numero.getText()), verificaNome(txt_Nome.getText()), cargo, cidade, partido);
                        if (ControladorCandidato.getInstance().cadastra(candidato)) {

                            JOptionPane.showMessageDialog(null, "Candidato cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar, número de candidato não permitido.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NullPointerException nullPointerException) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar, certifique-se de selecionar todas as caixas de seleção.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (NomeVazio ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar, nome em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
            if (e.getActionCommand().equals(Actions.MODIFICAR)) {
                int x = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja modificar o candidato?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION && verificaNumero(txt_ModificaNumero.getText())) {
                    try {
                        Cargo cargo = Cargo.valueOf(cBox_ModificaCargo.getSelectedItem().toString());
                        Partido partido = ControladorPartido.getInstance().getPartidoPorNome(cBox_ModificaPartido.getSelectedItem().toString());
                        Cidade cidade = ControladorCidade.getInstance().getCidade(cBox_ModificaCidade.getSelectedItem().toString());

                        Candidato candidato = new Candidato(Integer.parseInt(txt_ModificaNumero.getText()), verificaNome(txt_ModificaNome.getText()), cargo, cidade, partido);
                        ControladorCandidato.getInstance().modifica(candidatoModificado, candidato);
                    } catch (NullPointerException nullPointerException) {
                        JOptionPane.showMessageDialog(null, "Erro ao modificar, certifique-se de selecionar todas as caixas de seleção.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (NomeVazio ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao modificar, nome em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
            if (e.getActionCommand().equals(Actions.REMOVER)) {
                int x = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o candidato?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (ControladorCandidato.getInstance().remove(candidatoModificado)) {
                        btn_Modificar.setEnabled(false);
                        btn_Remove.setEnabled(false);
                    }
                }

            }
            if (e.getActionCommand().equals(Actions.PROCURAR_POR_NUMERO)) {
                if (verificaNumero(txt_ModificaNumero.getText())) {
                    candidatoModificado = ControladorCandidato.getInstance().getCandidato(Integer.parseInt(txt_ModificaNumero.getText()));
                    if (candidatoModificado != null) {
                        txt_ModificaNome.setText(candidatoModificado.getNome());
                        cBox_ModificaCargo.setSelectedItem(candidatoModificado.getCargo().toString());
                        cBox_ModificaCidade.setSelectedItem(candidatoModificado.getCidade().getNome());
                        cBox_ModificaPartido.setSelectedItem(candidatoModificado.getPartido().getNome());

                        btn_Modificar.setEnabled(true);
                        btn_Remove.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Candidato não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        btn_Modificar.setEnabled(false);
                        btn_Remove.setEnabled(false);
                    }
                }
            }

        }

        private boolean verificaNumero(String numero) {
            try {
                Integer.parseInt(numero);
                return true;
            } catch (NumberFormatException x) {
                JOptionPane.showMessageDialog(null, "Numero inválido, insira somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

    }
}
