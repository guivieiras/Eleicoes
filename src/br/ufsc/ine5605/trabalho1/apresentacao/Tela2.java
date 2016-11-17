/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author The Gui
 */
public class Tela2 extends JFrame {

    private javax.swing.JTabbedPane jTabbedPane;
    //Panel cadastro

    private javax.swing.JPanel panel_Cadastro;
    private javax.swing.JButton btn_Cadastro;
    private javax.swing.JTextField txt_Nome;
    private javax.swing.JTextField txt_Numero;
    private javax.swing.JComboBox<Cargo> cBox_Cargo;
    private javax.swing.JComboBox<Cidade> cBox_Cidade;
    private javax.swing.JComboBox<Partido> cBox_Partido;
    //panel modifica

    private javax.swing.JPanel panel_Modifica;
    private javax.swing.JButton btn_Modificar;
    private javax.swing.JButton btn_ProcuraPorNumero;
    private javax.swing.JButton btn_Remove;
    private javax.swing.JTextField txt_ModificaNome;
    private javax.swing.JTextField txt_ModificaNumero;
    private javax.swing.JComboBox<String> cBox_ModificaCargo;
    private javax.swing.JComboBox<String> cBox_ModificaCidade;
    private javax.swing.JComboBox<String> cBox_ModificaPartido;
    //panel lista

    private javax.swing.JPanel panel_Lista;
    private javax.swing.JTable jTable;

    public Tela2() {
        setTitle("Candidatos");
        initComponents();
    }

    private void initComponents() {

        jTabbedPane = new JTabbedPane();
        getContentPane().add(jTabbedPane);
        setSize(450, 350);

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

        txt_ModificaNome = new JTextField();
        txt_ModificaNumero = new JTextField();

        cBox_ModificaCidade = new JComboBox<>();
        cBox_ModificaPartido = new JComboBox<>();
        cBox_ModificaCargo = new JComboBox<>();

        panel_Modifica.setLayout(new GridBagLayout());

        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
     //   c.anchor = GridBagConstraints.NORTHWEST;

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


        c2.insets = new Insets(27, 10, 20, 15);
        c2.gridx = 2;
        c2.gridy = 5;
        c2.weightx = .01;
        c2.weighty = 1;
        panel_Modifica.add(btn_Modificar, c2);
        
        
        jTabbedPane.add("Modificar", panel_Modifica);
        //

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
