package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.constantes.Actions;
import br.ufsc.ine5605.trabalho1.entidade.Eleitor;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import br.ufsc.ine5605.trabalho1.entidade.Urna.Turno;
import java.awt.GridBagConstraints;
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

public class TelaVotacao extends JFrame{
    
    private final Urna urna;
    private final Eleitor eleitor;
    private final TelaMesario telaMesario;
    
    private JPanel jpanel;
    private final ActionManager actionManager = new ActionManager();
    
    JLabel label_Prefeito;
    JLabel label_Vereador;
     
    JTextField txtField_Prefeito;
    JTextField txtField_Vereador;
     
    JButton button_Votar;

    public TelaVotacao(Urna urna, Eleitor eleitor, TelaMesario telaMesario) {
        this.urna = urna;
        this.eleitor = eleitor;
        this.telaMesario = telaMesario;
        iniciaComponents();
        setButtonActions();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Votar");
    }
    public boolean verificaNumero(JTextField txt) {
        try {
            if (txt.getText().length() > 0) {
                Integer.parseInt(txt.getText());
            } else {
                txt.setText("00");
            }
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Erro ao votar, Insira apenas numeros.", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
 
    public boolean verificaNumeros() {
        return verificaNumero(txtField_Prefeito) && verificaNumero(txtField_Vereador);
    }
    public void votar() {
        if (verificaNumeros()) {
            urna.contabilizaVoto(Integer.parseInt(txtField_Prefeito.getText()), Integer.parseInt(txtField_Vereador.getText()));
            eleitor.votar();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
   
    private void votarSegundoTurno(){
        if (verificaNumero(txtField_Prefeito)){
            urna.contabilizaVoto(Integer.parseInt(txtField_Prefeito.getText()));
            eleitor.votar();
            
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
    
    private void iniciaComponents() {
        
        jpanel = new JPanel();
        getContentPane().add(jpanel);
        
        jpanel.setLayout(new GridBagLayout());
        setSize(320, 220);
        
        label_Prefeito = new JLabel("Voto para prefeito: ");
        txtField_Prefeito = new JTextField();
        button_Votar = new JButton("Votar");
        
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        
        layout.insets = new Insets(40, 20, 0, 20);
        layout.gridx = 0;
        layout.gridy = 0;
        layout.weightx = 0;
        jpanel.add(label_Prefeito, layout);
        
        layout.insets = new Insets(40, 20, 0, 20);
        layout.gridx = 1;
        layout.gridy = 0;
        layout.weightx = 1;
        jpanel.add(txtField_Prefeito, layout);
        
        if(urna.getTurno()==Turno.Primeiro){
            txtField_Vereador = new JTextField();
            label_Vereador = new JLabel("Voto para Vereador: ");
            
            layout.insets = new Insets(20, 20, 0, 20);
            layout.gridx = 0;
            layout.gridy = 1;
            layout.weightx = 0;
            jpanel.add(label_Vereador, layout);
            
            layout.insets = new Insets(20, 20, 0, 20);
            layout.gridx = 1;
            layout.gridy = 1;
            layout.weightx = 1;
            jpanel.add(txtField_Vereador, layout);
            
        }
        
        layout.insets = new Insets(0, 0, 0, 20);
        layout.gridx = 1;
        layout.gridy = 2;
        layout.weightx = 0;
        layout.weighty = 0.1;
        jpanel.add(button_Votar, layout);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                telaMesario.unlockTela();
            }
        });
    }

    private void setButtonActions() {
        button_Votar.addActionListener(actionManager);
        button_Votar.setActionCommand(Actions.VOTAR);
        
    }

    private class ActionManager implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if(urna.getTurno()==Turno.Primeiro){
                votar();
            } else{
                votarSegundoTurno();
            }
            
        }
    }
}