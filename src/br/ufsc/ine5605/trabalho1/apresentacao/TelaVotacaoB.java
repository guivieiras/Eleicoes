package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.constantes.Actions;
import br.ufsc.ine5605.trabalho1.controle.ControladorPartido;
import br.ufsc.ine5605.trabalho1.controle.ControladorPrincipal;
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

public class TelaVotacaoB extends JFrame{
    
    private final Urna urna;
    private final Eleitor eleitor;
    private final TelaMesario telaMesario;
    
    private JPanel jpanel;
    private ActionManager actionManager = new ActionManager();
    
    JLabel label_Prefeito;
    JLabel label_Vereador;
     
    JTextField txtField_Prefeito;
    JTextField txtfield_vereador;
     
    JButton button_Votar;

    public TelaVotacaoB(Urna urna, Eleitor eleitor, TelaMesario telaMesario) {
        this.urna = urna;
        this.eleitor = eleitor;
        this.telaMesario = telaMesario;
        iniciaComponents();
        setButtonActions();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
    }
    public void verificaNumero(JTextField txt) throws NumberFormatException{
        if (txt.getText().length() > 0) {
                Integer.parseInt(txt.getText());
        } else {
            txt.setText("00");
        }
    }
    public boolean verificaNumeros() {
        try {
            verificaNumero(txtField_Prefeito);
            verificaNumero(txtfield_vereador);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Erro ao votar, Insira apenas numeros.", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;

    }
    public void votar() {
        if (verificaNumeros()) {
            urna.contabilizaVoto(Integer.parseInt(txtField_Prefeito.getText()), Integer.parseInt(txtfield_vereador.getText()));
            eleitor.votar();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
   
    private void votarSegundoTurno() {
        boolean valido;
        try {
            verificaNumero(txtField_Prefeito);
            valido =true;
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Erro ao votar, Insira apenas numeros.", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            valido = false;
        }
        if (valido){
            urna.contabilizaVoto(Integer.parseInt(txtField_Prefeito.getText()));
            eleitor.votar();
            
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
    private void iniciaComponents() {
        
        jpanel = new JPanel();
        getContentPane().add(jpanel);
        
        jpanel.setLayout(new GridBagLayout());
        setSize(300, 200);
        
        label_Prefeito = new JLabel("Voto para prefeito");
        txtField_Prefeito = new JTextField();
        button_Votar = new JButton("Votar");
        
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        
        layout.insets = new Insets(60, 40, 0, 0);
        layout.gridx = 0;
        layout.gridy = 0;
        layout.weightx = 0;
        jpanel.add(label_Prefeito, layout);
        
        layout.insets = new Insets(60, 40, 0, 40);
        layout.gridx = 1;
        layout.gridy = 0;
        layout.weightx = 1;
        jpanel.add(txtField_Prefeito, layout);
        
        if(urna.getTurno()==Turno.Primeiro){
            txtfield_vereador = new JTextField();
            label_Vereador = new JLabel();
            
            layout.insets = new Insets(40, 40, 0, 0);
            layout.gridx = 0;
            layout.gridy = 1;
            layout.weightx = 0;
            jpanel.add(label_Vereador, layout);
            
            layout.insets = new Insets(40, 40, 0, 40);
            layout.gridx = 1;
            layout.gridy = 1;
            layout.weightx = 1;
            jpanel.add(txtfield_vereador, layout);
            
        }
        
        layout.insets = new Insets(0, 0, 40, 40);
        layout.gridx = 2;
        layout.gridy = 2;
        layout.weightx = 0;
        layout.weighty = 1;
        jpanel.add(button_Votar, layout);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ControladorPrincipal.getInstance().liberaTelaPrincipal();
                ControladorPartido.getInstance().persist();
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
