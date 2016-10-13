package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.controle.ControladorEleitor;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.exception.NomeVazio;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 10349509913
 *
 */
public class TelaEleitor extends JFrame {

    private final ControladorEleitor controladorEleitor;
    private Eleitor eleitorModificado;

    public TelaEleitor(ControladorEleitor controladorEleitor) {
        this.controladorEleitor = controladorEleitor;
        initComponents();
        listaEleitores();
        popularCheckBoxes();
        setLocationRelativeTo(null);
    }

    private void cadastraEleitor() {
        if (verificaTitulo(txt_Titulo.getText()) && verificaSecaoZona(txt_Secao.getText(), txt_Zona.getText())) {
            try {
                int secao = Integer.parseInt(txt_Secao.getText());
                int zona = Integer.parseInt(txt_Zona.getText());
                Cidade cidade = controladorEleitor.controladorPrincipal.controladorCidade.getCidade(cBox_Cidade.getSelectedItem().toString());

                Eleitor eleitor = new Eleitor(zona, secao, Long.parseLong(txt_Titulo.getText()), verificaNome(txt_Nome.getText()), cidade);
                if (controladorEleitor.cadastra(eleitor)) {
                    JOptionPane.showMessageDialog(null, "Eleitor cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Eleitor com o mesmo título ja cadastrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NomeVazio ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar, nome em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar, certifique-se de selecionar todas as caixas de seleção.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listaEleitores() {
        addRows(controladorEleitor.getLista());
    }

    private void procuraEleitorPorNome() {
        eleitorModificado = controladorEleitor.getEleitor(txt_ModificaNome.getText());
        setEleitorModificado(eleitorModificado);
    }

    private void procuraEleitorPorTitulo() {
        if (verificaTitulo(txt_ModificaTitulo.getText())) {
            eleitorModificado = controladorEleitor.getEleitor(Long.parseLong(txt_ModificaTitulo.getText()));
            setEleitorModificado(eleitorModificado);
        }
    }

    private void setEleitorModificado(Eleitor eleitorModificado) {
        if (eleitorModificado != null) {
            txt_ModificaNome.setText(eleitorModificado.getNome());
            txt_ModificaTitulo.setText(String.valueOf(eleitorModificado.getTitulo()));
            txt_ModificaSecao.setText(String.valueOf(eleitorModificado.getSecaoEleitoral()));
            txt_ModificaZona.setText(String.valueOf(eleitorModificado.getZonaEleitoral()));
            cBox_ModificaCidade.setSelectedItem(eleitorModificado.getCidade().getNome());

            btn_Modificar.setEnabled(true);
            btn_Remove.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Eleitor não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            btn_Modificar.setEnabled(false);
            btn_Remove.setEnabled(false);
        }
    }

    public void removeEleitor() {
        int x = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o eleitor?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (controladorEleitor.remove(eleitorModificado)) {
                btn_Modificar.setEnabled(false);
                btn_Remove.setEnabled(false);
            }
        }
    }

    public void modificaEleitor() {
        int x = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja modificar o eleitor?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION && verificaTitulo(txt_ModificaTitulo.getText()) && verificaSecaoZona(txt_ModificaSecao.getText(), txt_ModificaZona.getText())) {
            try {
                controladorEleitor.modifica(eleitorModificado, new Eleitor(
                        Integer.parseInt(txt_ModificaZona.getText()),
                        Integer.parseInt(txt_ModificaSecao.getText()),
                        Long.parseLong(txt_ModificaTitulo.getText()),
                        verificaNome(txt_ModificaNome.getText()), controladorEleitor.controladorPrincipal.controladorCidade.getCidade(
                        cBox_ModificaCidade.getSelectedItem().toString())));
            } catch (NomeVazio ex) {
                JOptionPane.showMessageDialog(null, "Erro ao modificar, nome em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void popularCheckBoxes() {
        for (Cidade cidade : controladorEleitor.controladorPrincipal.controladorCidade.getLista()) {
            cBox_Cidade.addItem(cidade.getNome());
            cBox_ModificaCidade.addItem(cidade.getNome());
        }

        cBox_Cidade.setSelectedIndex(-1);
        cBox_ModificaCidade.setSelectedIndex(-1);

    }

    public String verificaNome(String nome) throws NomeVazio {
        if (nome.length() == 0) {
            throw new NomeVazio();
        } else {
            return nome;
        }
    }

    private void addRows(ArrayList<Eleitor> eleitores) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        removeAllRows();
        for (Eleitor eleitor : eleitores) {
            model.addRow(new Object[]{eleitor.getNome(), eleitor.getTitulo(), eleitor.getCidade().getNome(), eleitor.getZonaEleitoral(), eleitor.getSecaoEleitoral()});
        }
    }

    private void removeAllRows() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private boolean verificaTitulo(String titulo) {
        if (titulo.length() != 12) {
            JOptionPane.showMessageDialog(null, "Título inválido, o título tem de ter 12 números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Long.parseLong(titulo);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Título inválido, insira somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean verificaSecaoZona(String secao, String zona) {
        try {
            Integer.parseInt(zona);
            Integer.parseInt(secao);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Seção ou Zona eleitoral incorreta, insira somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
        panel_Cadastro = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_Titulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_Cadastro = new javax.swing.JButton();
        txt_Nome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Zona = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_Secao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cBox_Cidade = new javax.swing.JComboBox<>();
        panel_Modifica = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_ModificaTitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_Modificar = new javax.swing.JButton();
        txt_ModificaNome = new javax.swing.JTextField();
        btn_ProcuraPorNome = new javax.swing.JButton();
        btn_ProcuraPorTitulo = new javax.swing.JButton();
        btn_Remove = new javax.swing.JButton();
        txt_ModificaZona = new javax.swing.JTextField();
        txt_ModificaSecao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cBox_ModificaCidade = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eleitores");
        setResizable(false);
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
                "Nome", "Título", "Cidade", "Zona", "Seção"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
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

        jLabel2.setText("Título");

        jLabel1.setText("Nome");

        btn_Cadastro.setText("Cadastrar");
        btn_Cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cadastro_CadastroActionPerformed(evt);
            }
        });

        jLabel5.setText("Zona");

        jLabel6.setText("Seção");

        jLabel9.setText("Cidade");

        javax.swing.GroupLayout panel_CadastroLayout = new javax.swing.GroupLayout(panel_Cadastro);
        panel_Cadastro.setLayout(panel_CadastroLayout);
        panel_CadastroLayout.setHorizontalGroup(
            panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CadastroLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_Nome, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(txt_Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(txt_Secao, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(txt_Zona, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(cBox_Cidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(txt_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Secao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Zona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(panel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_CadastroLayout.createSequentialGroup()
                    .addContainerGap(219, Short.MAX_VALUE)
                    .addComponent(btn_Cadastro)
                    .addGap(30, 30, 30)))
        );

        jTabbedPane2.addTab("Cadastro", panel_Cadastro);

        jLabel3.setText("Título");

        jLabel4.setText("Nome");

        btn_Modificar.setText("Modificar");
        btn_Modificar.setEnabled(false);
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Modificar_CadastroActionPerformed(evt);
            }
        });

        btn_ProcuraPorNome.setText("Procurar");
        btn_ProcuraPorNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProcuraPorNomeActionPerformed(evt);
            }
        });

        btn_ProcuraPorTitulo.setText("Procurar");
        btn_ProcuraPorTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProcuraPorTituloActionPerformed(evt);
            }
        });

        btn_Remove.setText("Remover");
        btn_Remove.setEnabled(false);
        btn_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveActionPerformed(evt);
            }
        });

        txt_ModificaSecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ModificaSecaoActionPerformed(evt);
            }
        });

        jLabel7.setText("Seção");

        jLabel8.setText("Zona");

        jLabel10.setText("Cidade");

        javax.swing.GroupLayout panel_ModificaLayout = new javax.swing.GroupLayout(panel_Modifica);
        panel_Modifica.setLayout(panel_ModificaLayout);
        panel_ModificaLayout.setHorizontalGroup(
            panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ModificaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ModificaLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btn_Remove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Modificar)
                        .addGap(25, 25, 25))
                    .addGroup(panel_ModificaLayout.createSequentialGroup()
                        .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_ModificaLayout.createSequentialGroup()
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10))
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_ModificaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                        .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_ModificaTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_ModificaNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panel_ModificaLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(cBox_ModificaCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(panel_ModificaLayout.createSequentialGroup()
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ModificaSecao, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ModificaZona, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ProcuraPorTitulo)
                            .addComponent(btn_ProcuraPorNome))
                        .addContainerGap())))
        );
        panel_ModificaLayout.setVerticalGroup(
            panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ModificaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ModificaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btn_ProcuraPorNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ModificaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btn_ProcuraPorTitulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cBox_ModificaCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ModificaSecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ModificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_ModificaZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
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
        controladorEleitor.controladorPrincipal.telaPrincipal.unlockTelaPrincipal();
    }//GEN-LAST:event_formWindowClosing

    private void btn_Cadastro_CadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cadastro_CadastroActionPerformed
        cadastraEleitor();
    }//GEN-LAST:event_btn_Cadastro_CadastroActionPerformed

    private void btn_Modificar_CadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Modificar_CadastroActionPerformed
        modificaEleitor();
    }//GEN-LAST:event_btn_Modificar_CadastroActionPerformed

    private void btn_ProcuraPorTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcuraPorTituloActionPerformed
        procuraEleitorPorTitulo();
    }//GEN-LAST:event_btn_ProcuraPorTituloActionPerformed

    private void btn_ProcuraPorNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcuraPorNomeActionPerformed
        procuraEleitorPorNome();
    }//GEN-LAST:event_btn_ProcuraPorNomeActionPerformed

    private void btn_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveActionPerformed
        removeEleitor();
    }//GEN-LAST:event_btn_RemoveActionPerformed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        if (jTabbedPane2.getSelectedIndex() == 0) {
            listaEleitores();
        }
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void txt_ModificaSecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ModificaSecaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ModificaSecaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cadastro;
    private javax.swing.JButton btn_Modificar;
    private javax.swing.JButton btn_ProcuraPorNome;
    private javax.swing.JButton btn_ProcuraPorTitulo;
    private javax.swing.JButton btn_Remove;
    private javax.swing.JComboBox<String> cBox_Cidade;
    private javax.swing.JComboBox<String> cBox_ModificaCidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panel_Cadastro;
    private javax.swing.JPanel panel_Lista;
    private javax.swing.JPanel panel_Modifica;
    private javax.swing.JTextField txt_ModificaNome;
    private javax.swing.JTextField txt_ModificaSecao;
    private javax.swing.JTextField txt_ModificaTitulo;
    private javax.swing.JTextField txt_ModificaZona;
    private javax.swing.JTextField txt_Nome;
    private javax.swing.JTextField txt_Secao;
    private javax.swing.JTextField txt_Titulo;
    private javax.swing.JTextField txt_Zona;
    // End of variables declaration//GEN-END:variables
}
