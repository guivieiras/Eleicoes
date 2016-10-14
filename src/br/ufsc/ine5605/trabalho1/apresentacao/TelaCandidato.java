package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.controle.ControladorCandidato;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cargo;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Partido;
import br.ufsc.ine5605.trabalho1.exception.NomeVazio;
import javax.swing.JOptionPane;

public class TelaCandidato extends Tela<Candidato> {

    private final ControladorCandidato controladorCandidato;
    private Candidato candidatoModificado;

    public TelaCandidato(ControladorCandidato controladorCandidato) {
        this.controladorCandidato = controladorCandidato;
        initComponents();
        setLocationRelativeTo(null);
        popularCheckBoxes();
    }

    private void cadastraCandidato() {
        if (verificaNumero(txt_Numero.getText())) {
            try {
                Cargo cargo = Cargo.valueOf(cBox_Cargo.getSelectedItem().toString());
                Partido partido = controladorCandidato.controladorPrincipal.controladorPartido.getPartidoPorNome(cBox_Partido.getSelectedItem().toString());
                Cidade cidade = controladorCandidato.controladorPrincipal.controladorCidade.getCidade(cBox_Cidade.getSelectedItem().toString());

                Candidato candidato = new Candidato(Integer.parseInt(txt_Numero.getText()), verificaNome(txt_Nome.getText()), cargo, cidade, partido);
                if (controladorCandidato.cadastra(candidato)) {

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

    private void listaCandidatos() {
        addRows(controladorCandidato.getLista(), jTable1);
    }

    private void removeCandidato() {
        int x = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o candidato?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (controladorCandidato.remove(candidatoModificado)) {
                btn_Modificar.setEnabled(false);
                btn_Remove.setEnabled(false);
            }
        }
    }

    private void modificaCandidato() {
        int x = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja modificar o candidato?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION && verificaNumero(txt_ModificaNumero.getText())) {
            try {
                Cargo cargo = Cargo.valueOf(cBox_ModificaCargo.getSelectedItem().toString());
                Partido partido = controladorCandidato.controladorPrincipal.controladorPartido.getPartidoPorNome(cBox_ModificaPartido.getSelectedItem().toString());
                Cidade cidade = controladorCandidato.controladorPrincipal.controladorCidade.getCidade(cBox_ModificaCidade.getSelectedItem().toString());

                Candidato candidato = new Candidato(Integer.parseInt(txt_ModificaNumero.getText()), verificaNome(txt_ModificaNome.getText()), cargo, cidade, partido);
                controladorCandidato.modifica(candidatoModificado, candidato);
            } catch (NullPointerException nullPointerException) {
                JOptionPane.showMessageDialog(null, "Erro ao modificar, certifique-se de selecionar todas as caixas de seleção.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NomeVazio ex) {
                JOptionPane.showMessageDialog(null, "Erro ao modificar, nome em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void procuraCandidatoPorNumero() {
        if (verificaNumero(txt_ModificaNumero.getText())) {
            candidatoModificado = controladorCandidato.getCandidato(Integer.parseInt(txt_ModificaNumero.getText()));
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

    private boolean verificaNumero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Numero inválido, insira somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void popularCheckBoxes() {
        for (Cargo cargo : Cargo.values()) {
            cBox_Cargo.addItem(cargo.toString());
            cBox_ModificaCargo.addItem(cargo.toString());
        }
        for (Partido partido : controladorCandidato.controladorPrincipal.controladorPartido.getLista()) {
            cBox_Partido.addItem(partido.getNome());
            cBox_ModificaPartido.addItem(partido.getNome());
        }
        for (Cidade cidade : controladorCandidato.controladorPrincipal.controladorCidade.getLista()) {
            cBox_Cidade.addItem(cidade.getNome());
            cBox_ModificaCidade.addItem(cidade.getNome());
        }
        cBox_Cargo.setSelectedIndex(-1);
        cBox_ModificaCargo.setSelectedIndex(-1);
        cBox_Cidade.setSelectedIndex(-1);
        cBox_ModificaCidade.setSelectedIndex(-1);
        cBox_Partido.setSelectedIndex(-1);
        cBox_ModificaPartido.setSelectedIndex(-1);
    }

    @Override
    Object[] atributosParaArray(Candidato candidato) {
        return new Object[]{candidato.getNome(), candidato.getNumero(), candidato.getPartido().getNome(), candidato.getCargo().toString(), candidato.getCidade().getNome()};
    }

    /*
    totalizar eleicao por cidade
    mostrar votos de cada urna e quais vereadores/prefeitos eleitos
     */
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
        panel_Cadastro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_Numero = new javax.swing.JTextField();
        txt_Nome = new javax.swing.JTextField();
        btn_Cadastro = new javax.swing.JButton();
        cBox_Cargo = new javax.swing.JComboBox<>();
        cBox_Cidade = new javax.swing.JComboBox<>();
        cBox_Partido = new javax.swing.JComboBox<>();
        panel_Modifica = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        txt_ModificaNumero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_Modificar = new javax.swing.JButton();
        txt_ModificaNome = new javax.swing.JTextField();
        btn_ProcuraPorNumero = new javax.swing.JButton();
        btn_Remove = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cBox_ModificaCargo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cBox_ModificaCidade = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cBox_ModificaPartido = new javax.swing.JComboBox<>();

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
                "Nome", "Numero", "Partido", "Cargo", "Cidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout panel_ListaLayout = new javax.swing.GroupLayout(panel_Lista);
        panel_Lista.setLayout(panel_ListaLayout);
        panel_ListaLayout.setHorizontalGroup(
            panel_ListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_ListaLayout.setVerticalGroup(
            panel_ListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Lista", panel_Lista);

        jLabel1.setText("Nome");

        jLabel2.setText("Número");

        jLabel5.setText("Cargo");

        jLabel6.setText("Cidade");

        jLabel7.setText("Partido");

        btn_Cadastro.setText("Cadastrar");
        btn_Cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cadastro_CadastroActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cBox_Cargo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cBox_Cidade, javax.swing.GroupLayout.Alignment.LEADING, 0, 122, Short.MAX_VALUE)
                        .addComponent(cBox_Partido, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(74, 74, 74))
            .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_CadastroLayout.createSequentialGroup()
                    .addContainerGap(289, Short.MAX_VALUE)
                    .addComponent(btn_Cadastro)
                    .addGap(25, 25, 25)))
        );
        panel_CadastroLayout.setVerticalGroup(
            panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CadastroLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cBox_Cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cBox_Partido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_CadastroLayout.createSequentialGroup()
                    .addContainerGap(219, Short.MAX_VALUE)
                    .addComponent(btn_Cadastro)
                    .addGap(30, 30, 30)))
        );

        jTabbedPane2.addTab("Cadastro", panel_Cadastro);

        jLabel99.setText("Número");

        jLabel4.setText("Nome");

        btn_Modificar.setText("Modificar");
        btn_Modificar.setEnabled(false);
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Modificar_CadastroActionPerformed(evt);
            }
        });

        btn_ProcuraPorNumero.setText("Procurar");
        btn_ProcuraPorNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProcuraPorNumeroActionPerformed(evt);
            }
        });

        btn_Remove.setText("Remover");
        btn_Remove.setEnabled(false);
        btn_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveActionPerformed(evt);
            }
        });

        jLabel8.setText("Cargo");

        jLabel9.setText("Cidade");

        jLabel10.setText("Partido");

        javax.swing.GroupLayout panel_ModificaLayout = new javax.swing.GroupLayout(panel_Modifica);
        panel_Modifica.setLayout(panel_ModificaLayout);
        panel_ModificaLayout.setHorizontalGroup(
            panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ModificaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ModificaLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(btn_Remove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Modificar)
                        .addGap(25, 25, 25))
                    .addGroup(panel_ModificaLayout.createSequentialGroup()
                        .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ModificaLayout.createSequentialGroup()
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel99)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ModificaNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ModificaNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ProcuraPorNumero))
                            .addGroup(panel_ModificaLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(68, 68, 68)
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cBox_ModificaCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cBox_ModificaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cBox_ModificaCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panel_ModificaLayout.setVerticalGroup(
            panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ModificaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ModificaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ModificaNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99)
                    .addComponent(btn_ProcuraPorNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cBox_ModificaCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cBox_ModificaCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cBox_ModificaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Modificar)
                    .addComponent(btn_Remove))
                .addGap(30, 30, 30))
        );

        jTabbedPane2.addTab("Modifica", panel_Modifica);

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
        controladorCandidato.controladorPrincipal.telaPrincipal.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void btn_Cadastro_CadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cadastro_CadastroActionPerformed
        cadastraCandidato();
    }//GEN-LAST:event_btn_Cadastro_CadastroActionPerformed

    private void btn_Modificar_CadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Modificar_CadastroActionPerformed
        modificaCandidato();
    }//GEN-LAST:event_btn_Modificar_CadastroActionPerformed

    private void btn_ProcuraPorNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcuraPorNumeroActionPerformed
        procuraCandidatoPorNumero();
    }//GEN-LAST:event_btn_ProcuraPorNumeroActionPerformed

    private void btn_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveActionPerformed
        removeCandidato();
    }//GEN-LAST:event_btn_RemoveActionPerformed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        if (jTabbedPane2.getSelectedIndex() == 0) {
            listaCandidatos();
        }
    }//GEN-LAST:event_jTabbedPane2StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cadastro;
    private javax.swing.JButton btn_Modificar;
    private javax.swing.JButton btn_ProcuraPorNumero;
    private javax.swing.JButton btn_Remove;
    private javax.swing.JComboBox<String> cBox_Cargo;
    private javax.swing.JComboBox<String> cBox_Cidade;
    private javax.swing.JComboBox<String> cBox_ModificaCargo;
    private javax.swing.JComboBox<String> cBox_ModificaCidade;
    private javax.swing.JComboBox<String> cBox_ModificaPartido;
    private javax.swing.JComboBox<String> cBox_Partido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panel_Cadastro;
    private javax.swing.JPanel panel_Lista;
    private javax.swing.JPanel panel_Modifica;
    private javax.swing.JTextField txt_ModificaNome;
    private javax.swing.JTextField txt_ModificaNumero;
    private javax.swing.JTextField txt_Nome;
    private javax.swing.JTextField txt_Numero;
    // End of variables declaration//GEN-END:variables

}
