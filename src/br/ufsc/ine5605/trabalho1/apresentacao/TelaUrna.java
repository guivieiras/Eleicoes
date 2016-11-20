package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.constantes.Actions;
import br.ufsc.ine5605.trabalho1.controle.ControladorCandidato;
import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.controle.ControladorCidade;
import br.ufsc.ine5605.trabalho1.controle.ControladorPartido;
import br.ufsc.ine5605.trabalho1.controle.ControladorPrincipal;
import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.entidade.Urna.Turno;
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
public class TelaUrna extends Tela<Urna> {

    private JTabbedPane jTabbedPane;
    private ActionManager actionManager = new ActionManager();

    //Panel lista
    private JPanel panel_Lista;
    private JTable jtable;
    private JButton btn_Remove;

    //Panel cadastro
    private JPanel panel_Cadastro;
    private JButton btn_Cadastro;
    private JTextField txt_Secao;
    private JTextField txt_Zona;
    private JTextField txt_Limite;
    private JComboBox<Cidade> cBox_Cidade;
    private JComboBox<Turno> cBox_Turno;
    //panel modifica

    public TelaUrna() {
        setTitle("Urnas");
        initComponents();
        initButtonActions();
        listaUrnas();
        popularCheckBoxes();

        jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void popularCheckBoxes() {
        for (Urna.Turno turno : Urna.Turno.values()) {
            cBox_Turno.addItem(turno);
        }
        for (Cidade cidade : ControladorCidade.getInstance().getLista()) {
            cBox_Cidade.addItem(cidade);
        }

        cBox_Turno.setSelectedIndex(-1);
        cBox_Cidade.setSelectedIndex(-1);
    }

    private void listaUrnas() {
        addRows(ControladorUrna.getInstance().getLista(), jtable);
    }

    private void initComponents() {

        jTabbedPane = new JTabbedPane();
        jTabbedPane.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            if (jTabbedPane.getSelectedIndex() == 0) {
                listaUrnas();
            }
        });
        getContentPane().add(jTabbedPane);
        setSize(450, 350);

        //Painel lista
        jtable = new JTable();
        panel_Lista = new JPanel(new GridBagLayout());
        btn_Remove = new JButton("Remover");

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

        panel_Lista.add(tableContainer, new GridBagConstraints(0, 0, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 50, 10), 0, 0));
        panel_Lista.add(btn_Remove, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(250, 10, 10, 10), 0, 0));
        jTabbedPane.add("Lista", panel_Lista);

        //Painel Cadastro
        panel_Cadastro = new JPanel();
        btn_Cadastro = new JButton("Cadastrar");

        txt_Secao = new JTextField();
        txt_Zona = new JTextField();
        txt_Limite = new JTextField();

        cBox_Cidade = new JComboBox<>();
        cBox_Turno = new JComboBox<>();

        panel_Cadastro.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;

        c1.insets = new Insets(40, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 0;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Seção"), c1);

        c1.insets = new Insets(40, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 0;
        c1.weightx = 1;
        panel_Cadastro.add(txt_Secao, c1);

        c1.insets = new Insets(15, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 1;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Zona"), c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 1;
        c1.weightx = 1;
        panel_Cadastro.add(txt_Zona, c1);

        c1.insets = new Insets(15, 60, 0, 0);
        c1.gridx = 0;
        c1.gridy = 2;
        c1.weightx = 0;
        panel_Cadastro.add(new JLabel("Limite"), c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 2;
        c1.weightx = 1;
        panel_Cadastro.add(txt_Limite, c1);

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
        panel_Cadastro.add(new JLabel("Turno"), c1);

        c1.insets = new Insets(15, 40, 0, 0);
        c1.gridx = 1;
        c1.gridy = 4;
        c1.weightx = 1;
        panel_Cadastro.add(cBox_Turno, c1);

        c1.insets = new Insets(27, 10, 20, 15);
        c1.gridx = 2;
        c1.gridy = 5;
        c1.weightx = .01;
        c1.weighty = 1;
        panel_Cadastro.add(btn_Cadastro, c1);

        jTabbedPane.add("Cadastro", panel_Cadastro);

        //
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ControladorPrincipal.getInstance().liberaTelaPrincipal();
                ControladorUrna.getInstance().persist();
            }
        });
    }

    private void initButtonActions() {
        btn_Cadastro.addActionListener(actionManager);
        btn_Cadastro.setActionCommand(Actions.CADASTRAR);

        btn_Remove.addActionListener(actionManager);
        btn_Remove.setActionCommand(Actions.REMOVER);
    }

    @Override
    Object[] atributosParaArray(Urna urna) {
        return new Object[]{urna.getCidade().getNome(), urna.getSecaoEleitoral(), urna.getZonaEleitoral(), urna.getTurno().toString()};
    }

    private boolean verficaSecaoZonaLimite(String secao, String zona, String limite) {
        try {
            Integer.parseInt(secao);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Seção invalida, insira somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(zona);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Zona eleitoral inválida, insira somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(limite);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Limite de eleitores inválido, insira somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    class ActionManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(Actions.CADASTRAR)) {
                String secao = txt_Secao.getText();
                String zona = txt_Zona.getText();
                String limite = txt_Limite.getText();

                if (verficaSecaoZonaLimite(secao, zona, limite)) {
                    try {
                        Cidade cidade = ControladorCidade.getInstance().getCidade(cBox_Cidade.getSelectedItem().toString());
                        Urna urna = new Urna(Integer.parseInt(limite), Integer.parseInt(secao), Integer.parseInt(zona), cidade, ControladorCandidato.getInstance().getLista(cidade), Urna.Turno.valueOf(cBox_Turno.getSelectedItem().toString()));
                        if (ControladorUrna.getInstance().cadastra(urna)) {
                            JOptionPane.showMessageDialog(null, "Urna cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Urna da mesma seção, zona eleitoral e cidade já está cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NullPointerException npe) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar, certifique-se de selecionar todas as caixas de seleção.", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }

            if (e.getActionCommand().equals(Actions.REMOVER)) {
                int indexLinha = jtable.getSelectedRow();
                if (indexLinha >= 0) {
                    int secao = (int) jtable.getModel().getValueAt(indexLinha, 1);
                    int zona = (int) jtable.getModel().getValueAt(indexLinha, 2);
                    String city = (String) jtable.getModel().getValueAt(indexLinha, 0);
                    Cidade cidade = ControladorCidade.getInstance().getCidade(city);

                    int x = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover a urna?", "Aviso", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        if (ControladorUrna.getInstance().remove(ControladorUrna.getInstance().getUrna(secao, zona, cidade))) {
                            DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
                            dtm.removeRow(indexLinha);
                            JOptionPane.showMessageDialog(null, "Urna removida com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma urna antes de tentar remover", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
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
