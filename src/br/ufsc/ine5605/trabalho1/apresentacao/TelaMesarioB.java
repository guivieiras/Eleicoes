/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Matheus
 */
public class TelaMesarioB extends JFrame{
    
    private final Urna urna;
    
    JLabel label_Secao;
    JLabel label_Zona;
    JLabel label_Cidade;
    JLabel label_Titulo;
    
    
    public TelaMesarioB(Urna urna){
        this.urna=urna;
        iniciaComponents();
        setButtonAction();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Mesário");
    }

    private void iniciaComponents() {
        label_Secao = new JLabel("Secao: ");
        label_Zona = new JLabel("Zona: ");
        label_Cidade = new JLabel("Cidade: ");
        label_Titulo = new JLabel("Título: ");
    }

    private void setButtonAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
