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
public class TelaCidadeB extends Tela<Cidade> {

    private JTabbedPane jTabbedPane;
    private ActionManager actionManager = new ActionManager();
    private Cidade cidadeModificada;

    //Panel lista
    private JPanel panel_Lista;
    private JTable jtable;

    //Panel cadastro
    private JPanel panel_Cadastro;
    private JButton btn_Cadastro;
    private JTextField txt_Nome;

    //panel modifica
    private JPanel panel_Modifica;
    private JButton btn_Modificar;
    private JButton btn_ProcuraPorNome;
    private JButton btn_Remove;
    private JTextField txt_ModificaNome;

    //panel lista
    public TelaCidadeB() {
        setTitle("Candidatos");
        initComponents();
    }

    private void listaCandidatos() {
        addRows(ControladorCidade.getInstance().getLista(), jtable);
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
                    "Nome"
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

        c1.insets = new Insets(15, 20, 0, 20);
        c1.gridx = 2;
        c1.gridy = 1;
        c1.weightx = 0;
        panel_Modifica.add(btn_ProcuraPorNome, c1);

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
        btn_Remove = new JButton("Remover");

        txt_ModificaNome = new JTextField();

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

        c2.insets = new Insets(15, 20, 0, 20);
        c2.gridx = 2;
        c2.gridy = 1;
        c2.weightx = 0;
        panel_Modifica.add(btn_ProcuraPorNome, c2);

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
    Object[] atributosParaArray(Cidade cidade) {
        return new Object[]{cidade.getNome()};
    }

    class ActionManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(Actions.CADASTRAR)) {
                try {
                    Cidade cidade = new Cidade(verificaNome(txt_Nome.getText()));
                    if (ControladorCidade.getInstance().cadastra(cidade)) {
                        JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar a cidade", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NomeVazio ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar, nome em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }
            if (e.getActionCommand().equals(Actions.MODIFICAR)) {
                int x = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja modificar a cidade?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    try {
                        ControladorCidade.getInstance().modifica(cidadeModificada, new Cidade(verificaNome(txt_ModificaNome.getText())));
                    } catch (NomeVazio ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao modificar, nome em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (e.getActionCommand().equals(Actions.REMOVER)) {
                int x = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover a cidade?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (ControladorCidade.getInstance().remove(cidadeModificada)) {
                        btn_Modificar.setEnabled(false);
                        btn_Remove.setEnabled(false);
                    }
                }
            }
            if (e.getActionCommand().equals(Actions.PROCURAR_POR_NOME)) {
                cidadeModificada = ControladorCidade.getInstance().getCidade(txt_ModificaNome.getText());
                if (cidadeModificada != null) {
                    txt_ModificaNome.setText(cidadeModificada.getNome());

                    btn_Modificar.setEnabled(true);
                    btn_Remove.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Cidade não encontrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    btn_Modificar.setEnabled(false);
                    btn_Remove.setEnabled(false);
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
