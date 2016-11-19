package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.constantes.Actions;
import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.controle.ControladorCidade;
import br.ufsc.ine5605.trabalho1.controle.ControladorPartido;
import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.exception.NomeVazio;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author 10349509913
 */
public class TelaUrnaB extends Tela<Urna>{
    
    private final ControladorUrna controladorUrna = ControladorUrna.getInstance();
    
    private JTabbedPane jTabbedPane;
    private ActionManager actionManager = new ActionManager();
    private Urna urnaModificado;

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
    private JComboBox<Cargo> cBox_ModificaCargo;
    private JComboBox<Cidade> cBox_ModificaCidade;
    private JComboBox<Partido> cBox_ModificaPartido;
    //panel lista

    public TelaUrnaB() {
        setTitle("Urnas");
        initComponents();
        initButtonActions();
        listaUrnas();

    }

    private void listaUrnas() {
        addRows(ControladorUrna.getInstance().getLista(), jtable);
    }

    private void initComponents() {

        jTabbedPane = new JTabbedPane();
        jTabbedPane.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            addRows(ControladorUrna.getInstance().getLista(), jtable);
        });
        getContentPane().add(jTabbedPane);
        setSize(450, 350);

        //Painel lista
        jtable = new JTable();
        panel_Lista = new JPanel();

        JScrollPane tableContainer = new JScrollPane(jtable);

        jtable.setModel(new javax.swing.table.DefaultTableModel(
                new String[][]{}, new String[]{
                    "Cidade", "Seção", "Zona", "Turno"
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

    private void initButtonActions() {
        btn_Cadastro.addActionListener(actionManager);
        btn_Cadastro.setActionCommand(Actions.CADASTRAR);

        btn_Modificar.addActionListener(actionManager);
        btn_Modificar.setActionCommand(Actions.MODIFICAR);

        btn_ProcuraPorNumero.addActionListener(actionManager);
        btn_ProcuraPorNumero.setActionCommand(Actions.PROCURAR_POR_NUMERO);

        btn_Remove.addActionListener(actionManager);
        btn_Remove.setActionCommand(Actions.REMOVER);
    }

    @Override
    Object[] atributosParaArray(Urna urna) {
        return new Object[]{urna.getCidade()};
    }

    class ActionManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(Actions.CADASTRAR)) {
               
            }
            if (e.getActionCommand().equals(Actions.MODIFICAR)) {
               

            }
            if (e.getActionCommand().equals(Actions.REMOVER)) {
               
            }
            if (e.getActionCommand().equals(Actions.PROCURAR_POR_NUMERO)) {
               
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
