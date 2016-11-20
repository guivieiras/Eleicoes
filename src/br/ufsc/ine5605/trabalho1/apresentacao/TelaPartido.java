/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.constantes.Actions;
import static br.ufsc.ine5605.trabalho1.constantes.Actions.CADASTRAR;
import br.ufsc.ine5605.trabalho1.controle.ControladorPartido;
import br.ufsc.ine5605.trabalho1.controle.ControladorPrincipal;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.exception.NomeVazio;
import br.ufsc.ine5605.trabalho1.exception.PartidoDuplicado;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class TelaPartido extends Tela<Partido> {

    private JTabbedPane jTabbedPane;
    private ActionManager actionManager = new ActionManager();
    private Partido partidoModificado;

    // Panel Lista
    private JPanel jpnLista;
    private JTable jTable;

    //Panel Cadastro
    private JPanel jpnCadastro;

    private JLabel jlbNome;
    private JLabel jlbSigla;

    private JButton jbtCadastro;

    private JTextField jtfNome;
    private JTextField jtfSigla;

    //Panel Modifica
    private JPanel jpnModifica;

    private JButton jbtModificar;
    private JButton jbtProcuraPorNome;
    private JButton jbtRemove;

    private JLabel jlbNomeModifica;
    private JLabel jlbSiglaModifica;

    private JTextField jtfModificaNome;
    private JTextField jtfModificaSigla;

    public TelaPartido() {
        setTitle("Partidos");
        initComponents();
        setButtonActions();
        listaPartidos();
    }

    private void listaPartidos() {
        addRows(ControladorPartido.getInstance().getLista(), jTable);
    }

    private void initComponents() {

        jTabbedPane = new JTabbedPane();
        jTabbedPane.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            if (jTabbedPane.getSelectedIndex() == 0) {
                listaPartidos();
            }
        });
        getContentPane().add(jTabbedPane);
        setSize(450, 350);

        //Lista
        jTable = new JTable();
        jpnLista = new JPanel(new GridBagLayout());

        JScrollPane tableContainer = new JScrollPane(jTable);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
                new String[][]{}, new String[]{
                    "Nome", "Sigla"
                }
        ) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });

        jpnLista.add(tableContainer, new GridBagConstraints(0, 0, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
        jTabbedPane.add("Lista", jpnLista);

        //Cadastro
        jpnCadastro = new JPanel(new GridBagLayout());
        jbtCadastro = new JButton("Cadastrar");

        jtfNome = new JTextField();
        jtfSigla = new JTextField();

        jlbNome = new JLabel("Nome");
        jlbSigla = new JLabel("Sigla");

        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;

        c1.insets = new Insets(40, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 0;
        c1.weightx = 0;
        jpnCadastro.add(jlbNome, c1);

        c1.insets = new Insets(40, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 0;
        c1.weightx = 1;
        jpnCadastro.add(jtfNome, c1);

        c1.insets = new Insets(15, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 1;
        c1.weightx = 0;
        jpnCadastro.add(jlbSigla, c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 1;
        c1.weightx = 1;
        jpnCadastro.add(jtfSigla, c1);

        c1.insets = new Insets(27, 10, 20, 15);
        c1.gridx = 2;
        c1.gridy = 5;
        c1.weightx = .01;
        c1.weighty = 1;
        jpnCadastro.add(jbtCadastro, c1);

        jTabbedPane.add("Cadastro", jpnCadastro);

        //Modifica
        jpnModifica = new JPanel(new GridBagLayout());

        jbtProcuraPorNome = new JButton("Pesquisar");
        jbtModificar = new JButton("Modificar");
        jbtRemove = new JButton("Remover");
        jbtModificar.setEnabled(false);
        jbtRemove.setEnabled(false);

        jtfModificaNome = new JTextField();
        jtfModificaSigla = new JTextField();

        jlbNomeModifica = new JLabel("Nome");
        jlbSiglaModifica = new JLabel("Sigla");

        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;

        c2.insets = new Insets(40, 60, 0, 0);
        c2.gridx = 0;
        c2.gridy = 0;
        c2.weightx = 0;
        jpnModifica.add(jlbNomeModifica, c2);

        c2.insets = new Insets(40, 40, 0, 0);
        c2.gridx = 1;
        c2.gridy = 0;
        c2.weightx = 1;
        jpnModifica.add(jtfModificaNome, c2);

        c2.insets = new Insets(40, 20, 0, 20);
        c2.gridx = 2;
        c2.gridy = 0;
        c2.weightx = 0;
        jpnModifica.add(jbtProcuraPorNome, c2);

        c2.insets = new Insets(15, 60, 0, 0);
        c2.gridx = 0;
        c2.gridy = 1;
        c2.weightx = 0;
        jpnModifica.add(jlbSiglaModifica, c2);

        c2.insets = new Insets(15, 40, 0, 0);
        c2.gridx = 1;
        c2.gridy = 1;
        c2.weightx = 1;
        jpnModifica.add(jtfModificaSigla, c2);

        c2.insets = new Insets(27, 100, 20, 15);
        c2.gridx = 1;
        c2.gridy = 5;
        c2.weightx = 0;
        c2.fill = GridBagConstraints.NONE;
        c2.weighty = 1;
        jpnModifica.add(jbtRemove, c2);

        c2.insets = new Insets(27, 5, 20, 15);
        c2.gridx = 2;
        c2.gridy = 5;
        c2.weightx = 0.1;
        c2.weighty = 1;
        jpnModifica.add(jbtModificar, c2);

        jTabbedPane.add("Modificar", jpnModifica);
        //

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ControladorPrincipal.getInstance().liberaTelaPrincipal();
                ControladorPartido.getInstance().persist();
            }
        });
    }

    private void setButtonActions() {
        jbtCadastro.addActionListener(actionManager);
        jbtCadastro.setActionCommand(Actions.CADASTRAR);

        jbtModificar.addActionListener(actionManager);
        jbtModificar.setActionCommand(Actions.MODIFICAR);

        jbtProcuraPorNome.addActionListener(actionManager);
        jbtProcuraPorNome.setActionCommand(Actions.PROCURAR_POR_NOME);

        jbtRemove.addActionListener(actionManager);
        jbtRemove.setActionCommand(Actions.REMOVER);

    }

    @Override
    Object[] atributosParaArray(Partido classe) {
        return new Object[]{classe.getNome(), classe.getSigla()};
    }

    class ActionManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(Actions.CADASTRAR)) {
                try {
                    Partido partido = new Partido(verificaNome(jtfNome.getText()), verificaNome(jtfSigla.getText()));
                    if (ControladorPartido.getInstance().cadastra(partido)) {
                        JOptionPane.showMessageDialog(null, "Partido cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar partido", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NomeVazio | PartidoDuplicado ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar, " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getActionCommand().equals(Actions.MODIFICAR)) {
                int x = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja modificar o partido?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    try {

                        Partido partido = new Partido(verificaNome(jtfModificaNome.getText()), verificaNome(jtfModificaSigla.getText()));
                        ControladorPartido.getInstance().modifica(partidoModificado, partido);

                    } catch (NomeVazio | PartidoDuplicado ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao modificar, " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            if (e.getActionCommand().equals(Actions.REMOVER)) {
                int x = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o partido?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (ControladorPartido.getInstance().remove(partidoModificado)) {
                        jbtModificar.setEnabled(false);
                        jbtRemove.setEnabled(false);
                    }
                }

            }

            if (e.getActionCommand().equals(Actions.PROCURAR_POR_NOME)) {

                if (!jtfModificaNome.getText().isEmpty()) {
                    partidoModificado = ControladorPartido.getInstance().getPartidoPorNome(jtfModificaNome.getText());

                    if (partidoModificado != null) {
                        jtfModificaNome.setText(partidoModificado.getNome());
                        jtfModificaSigla.setText(partidoModificado.getSigla());

                        jbtModificar.setEnabled(true);
                        jbtRemove.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Eleitor não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jbtModificar.setEnabled(false);
                        jbtRemove.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Campo de nome vazio", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jbtModificar.setEnabled(false);
                    jbtRemove.setEnabled(false);
                }
            }

        }
    }

}
