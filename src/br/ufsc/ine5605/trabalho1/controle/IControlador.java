/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.controle;

import java.util.ArrayList;

public interface IControlador<T> {
    public boolean cadastra(T item);
    
    public boolean remove(T item);
    
    public boolean modifica(T old, T novo);
    
    public ArrayList<T> getLista ();
    
    public void exibeTela();
    
}
