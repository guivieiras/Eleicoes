/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.apresentacao;



import br.ufsc.ine5605.trabalho1.constantes.Actions;
import br.ufsc.ine5605.trabalho1.controle.ControladorEleitor;
import br.ufsc.ine5605.trabalho1.controle.ControladorPartido;
import br.ufsc.ine5605.trabalho1.controle.ControladorPrincipal;
import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.entidade.Urna.Turno;
import java.awt.Component;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.REMAINDER;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Matheus
 */
public class TelaMesario extends JFrame{
    
    private final Urna urna;
    private final ActionManager actionManager;
    
    JPanel panel;
    
    JLabel label_Secao;
    JLabel label_Zona;
    JLabel label_Cidade;
    JLabel label_Titulo;
    
    JButton button_Titulo;
    
    JTextField txtField_Titulo;
    
    public TelaMesario(Urna urna){
        actionManager = new ActionManager();
        this.urna=urna;
        iniciaComponents();
        setActions();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(340, 170);
        setTitle("Mesário");
    }
    
    private void exibeTelaVotacao() {
        String titulo = txtField_Titulo.getText();
        if (verificaTitulo(titulo)) {
            Eleitor eleitor = ControladorEleitor.getInstance().getEleitor(Long.parseLong(titulo));
            if (eleitor != null) {
                int magicNumber = ControladorUrna.getInstance().verificaEleitor(urna, eleitor);
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
                if (magicNumber == 3) {
                    JOptionPane.showMessageDialog(null, "Eleitor já votou.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Eleitor não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private boolean verificaTitulo(String titulo) {
        try {
            if (titulo.length() != 12) {
                JOptionPane.showMessageDialog(null, "Título inválido, o título tem de ter 12 números.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            Long.parseLong(titulo);

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Insira apenas numeros.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    private void iniciaComponents() {
        
        panel = new JPanel();
        getContentPane().add(panel);
        
        label_Secao = new JLabel("Secao: "+urna.getSecaoEleitoral());
        label_Zona = new JLabel("Zona: "+urna.getZonaEleitoral());
        label_Cidade = new JLabel("Cidade: "+urna.getCidade());
        label_Titulo = new JLabel("Título: ");
        
        button_Titulo=new JButton("Continuar");
        
        txtField_Titulo = new JTextField();
        
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        
        layout.insets = new Insets(10, 10, 0, 10);
        layout.gridx = 0;
        layout.gridy = 0;
        layout.weightx=0.5;
        panel.add(label_Secao, layout);
        
        layout.insets = new Insets(10, 10, 0, 10);
        layout.gridx = 1;
        layout.gridy = 0;
        layout.weightx=0;
        panel.add(label_Zona, layout);
        
        
        layout.insets = new Insets(10, 10, 0, 10);
        layout.gridx = 2;
        layout.gridy = 0;
        layout.weightx=1;
        layout.gridwidth=2;

        panel.add(label_Cidade, layout);
        
        
        layout.insets = new Insets(10, 10, 10, 20);
        layout.gridx = 0;
        layout.gridy = 1;
        layout.weightx=0;
        layout.weighty=0.01;
        panel.add(label_Titulo, layout);
        
        layout.insets = new Insets(10, 10, 10, 10);
        layout.gridx = 1;
        layout.gridwidth=REMAINDER;
        layout.gridy = 1;
        panel.add(txtField_Titulo, layout);
        
        layout.insets = new Insets(10, 0, 10, 10);
        layout.gridx = 3;
        layout.gridy = 2;
        panel.add(button_Titulo, layout);
        
        
    }

    private void setActions() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                urna.encerra();
                ControladorPrincipal.getInstance().testaFimEleicao();
            }
        });
        button_Titulo.addActionListener(actionManager);
        button_Titulo.setActionCommand("xablau");
    }
    void unlockTela() {
        this.setEnabled(true);
    }

    
    private class ActionManager implements ActionListener {
        

        @Override
        public void actionPerformed(ActionEvent e) {
            exibeTelaVotacao();
            
        }

    }
}