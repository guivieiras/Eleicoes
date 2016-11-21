package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.controle.ControladorCandidato;
import br.ufsc.ine5605.trabalho1.controle.ControladorCidade;
import br.ufsc.ine5605.trabalho1.controle.ControladorPrincipal;
import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.exception.CandidatosInsuficientes;
import br.ufsc.ine5605.trabalho1.exception.TurnoInvalido;
import br.ufsc.ine5605.trabalho1.exception.UrnaDuplicada;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TelaUrnaOLD extends Tela<Urna> {

    private final ControladorUrna controladorUrna;

    public TelaUrnaOLD(ControladorUrna controladorUrna) {
        this.controladorUrna = controladorUrna;
        initComponents();
        setLocationRelativeTo(null);
        popularCheckBoxes();
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void cadastraUrna() {

        String secao = txt_Secao.getText();
        String zona = txt_Zona.getText();
        String limite = txt_Limite.getText();

        if (verficaSecaoZonaLimite(secao, zona, limite)) {
            try {
                Cidade cidade = ControladorCidade.getInstance().getCidade(cBox_Cidade.getSelectedItem().toString());
                Urna urna = new Urna(Integer.parseInt(limite), Integer.parseInt(secao), Integer.parseInt(zona), cidade, ControladorCandidato.getInstance().getLista(cidade), Urna.Turno.valueOf(cBox_Turno.getSelectedItem().toString()));
                if (controladorUrna.cadastra(urna)) {
                    JOptionPane.showMessageDialog(null, "Urna cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Urna da mesma seção, zona eleitoral e cidade já está cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar, certifique-se de selecionar todas as caixas de seleção.", "Erro", JOptionPane.ERROR_MESSAGE);

            } catch (UrnaDuplicada ex) {
                Logger.getLogger(TelaUrnaOLD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TurnoInvalido ex) {
                Logger.getLogger(TelaUrnaOLD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CandidatosInsuficientes ex) {
                Logger.getLogger(TelaUrnaOLD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void listaUrnas() {
        addRows(controladorUrna.getLista(), jTable1);
    }

    private void removeUrna() {
        int indexLinha = jTable1.getSelectedRow();
        int secao = (int) jTable1.getModel().getValueAt(indexLinha, 1);
        int zona = (int) jTable1.getModel().getValueAt(indexLinha, 2);
        String city = (String) jTable1.getModel().getValueAt(indexLinha, 0);
        Cidade cidade = ControladorCidade.getInstance().getCidade(city);

        int x = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover a urna?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (controladorUrna.remove(controladorUrna.getUrna(secao, zona, cidade))) {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.removeRow(indexLinha);
                JOptionPane.showMessageDialog(this, "Urna removida com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void popularCheckBoxes() {
        for (Urna.Turno turno : Urna.Turno.values()) {
            cBox_Turno.addItem(turno.toString());
        }
        for (Cidade cidade : ControladorCidade.getInstance().getLista()) {
            cBox_Cidade.addItem(cidade.getNome());
        }

        cBox_Cidade.setSelectedIndex(-1);
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

    @Override
    Object[] atributosParaArray(Urna urna) {
        return new Object[]{urna.getCidade().getNome(), urna.getSecaoEleitoral(), urna.getZonaEleitoral(), urna.getTurno().toString()};
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        panel_Lista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_Remove = new javax.swing.JButton();
        panel_Cadastro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_Zona = new javax.swing.JTextField();
        txt_Secao = new javax.swing.JTextField();
        btn_Cadastro = new javax.swing.JButton();
        cBox_Cidade = new javax.swing.JComboBox<>();
        txt_Limite = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cBox_Turno = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cidade", "Seção", "Zona", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btn_Remove.setText("Remover");
        btn_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ListaLayout = new javax.swing.GroupLayout(panel_Lista);
        panel_Lista.setLayout(panel_ListaLayout);
        panel_ListaLayout.setHorizontalGroup(
            panel_ListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ListaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Remove)))
                .addContainerGap())
        );
        panel_ListaLayout.setVerticalGroup(
            panel_ListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Remove)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Lista", panel_Lista);

        jLabel1.setText("Seção");

        jLabel2.setText("Zona");

        jLabel5.setText("Cidade");

        btn_Cadastro.setText("Cadastrar");
        btn_Cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cadastro_CadastroActionPerformed(evt);
            }
        });

        jLabel3.setText("Limite de eleitores");

        jLabel6.setText("Turno");

        jLabel4.setText("<html>OBS: Ao cadastrar a urna para determinada cidade, os candidatos da mesma serão associados à urna.</html>");
        jLabel4.setAutoscrolls(true);

        javax.swing.GroupLayout panel_CadastroLayout = new javax.swing.GroupLayout(panel_Cadastro);
        panel_Cadastro.setLayout(panel_CadastroLayout);
        panel_CadastroLayout.setHorizontalGroup(
            panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_CadastroLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Secao, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Zona, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Limite, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cBox_Turno, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_CadastroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Cadastro)
                .addGap(25, 25, 25))
            .addGroup(panel_CadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_CadastroLayout.setVerticalGroup(
            panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CadastroLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Secao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Zona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Limite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cBox_Turno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btn_Cadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jTabbedPane2.addTab("Cadastro", panel_Cadastro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ControladorPrincipal.getInstance().liberaTelaPrincipal();
    }//GEN-LAST:event_formWindowClosing

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        if (jTabbedPane2.getSelectedIndex() == 0) {
            listaUrnas();
        }
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void btn_Cadastro_CadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cadastro_CadastroActionPerformed
        cadastraUrna();
    }//GEN-LAST:event_btn_Cadastro_CadastroActionPerformed

    private void btn_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveActionPerformed
        removeUrna();
    }//GEN-LAST:event_btn_RemoveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cadastro;
    private javax.swing.JButton btn_Remove;
    private javax.swing.JComboBox<String> cBox_Cidade;
    private javax.swing.JComboBox<String> cBox_Turno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panel_Cadastro;
    private javax.swing.JPanel panel_Lista;
    private javax.swing.JTextField txt_Limite;
    private javax.swing.JTextField txt_Secao;
    private javax.swing.JTextField txt_Zona;
    // End of variables declaration//GEN-END:variables

}