/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import javax.swing.JOptionPane;

/**
 *
 * @author The Gui
 */
public class TelaMesario extends javax.swing.JFrame {

    /**
     * Creates new form TelaMesarios
     */
    private final ControladorUrna controladorUrna;
    private final Urna urna;

    public TelaMesario(ControladorUrna controladorUrna, Urna urna) {
        this.controladorUrna = controladorUrna;
        this.urna = urna;
        initComponents();
        lbl_Secao.setText(String.valueOf(urna.getSecaoEleitoral()));
        lbl_Zona.setText(String.valueOf(urna.getZonaEleitoral()));
        lbl_Cidade.setText(urna.getCidade().getNome());
        setLocationRelativeTo(null);
    }

    public void exibirTelaVotacao() {
        if (verificaTitulo(txt_Titulo.getText())) {
            Eleitor eleitor = controladorUrna.controladorPrincipal.controladorEleitor.getEleitor(Long.parseLong(txt_Titulo.getText()));
            if (eleitor != null) {
                int magicNumber = controladorUrna.verificaEleitor(urna, eleitor);
                if (magicNumber == 0) {
                    TelaVotacao tv = new TelaVotacao(urna, eleitor, this);
                    this.setEnabled(false);
                    tv.setVisible(true);
                }
                if (magicNumber == 1) {
                    JOptionPane.showMessageDialog(null, "Eleitor não pode votar pois a urna atingiu seu limite.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                if (magicNumber == 2) {
                    JOptionPane.showMessageDialog(null, "Eleitor está votando na urna errada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Eleitor não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void unlockTela() {
        this.setEnabled(true);
    }

    public boolean verificaTitulo(String titulo) {
        try {
            if (titulo.length() != 12) {
                JOptionPane.showMessageDialog(null, "Título inválido, o título tem de ter 12 números.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            Long.parseLong(titulo);

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Eleitor não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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

        txt_Titulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lbl_Secao = new javax.swing.JLabel();
        lbl_Zona = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_Cidade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Urna");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Seção:");

        jLabel2.setText("Zona:");

        jLabel7.setText("Titulo:");

        jButton1.setText("Votar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Cidade:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Titulo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Secao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Zona, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Cidade, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Zona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(lbl_Secao, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbl_Cidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exibirTelaVotacao();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        urna.encerra();
        if (controladorUrna.urnasEmExecucao() == 0) {
            controladorUrna.controladorPrincipal.telaPrincipal.unlockTelaPrincipal();
            controladorUrna.controladorPrincipal.telaPrincipal.unlockBotaoResultado();
        }

    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbl_Cidade;
    private javax.swing.JLabel lbl_Secao;
    private javax.swing.JLabel lbl_Zona;
    private javax.swing.JTextField txt_Titulo;
    // End of variables declaration//GEN-END:variables

}