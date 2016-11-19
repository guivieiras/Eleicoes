/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.exception.NomeVazio;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class Tela<T> extends JFrame {

    void addRows(ArrayList<T> objetos, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        removeAllRows(table);
        for (T objeto : objetos) {
            model.addRow(atributosParaArray(objeto));
        }
    }

    String verificaNome(String nome) throws NomeVazio {
        if (nome.length() == 0) {
            throw new NomeVazio();
        } else {
            return nome;
        }
    }

    abstract Object[] atributosParaArray(T classe);

    void removeAllRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

}
