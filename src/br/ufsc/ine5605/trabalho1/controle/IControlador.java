package br.ufsc.ine5605.trabalho1.controle;

import java.util.ArrayList;

public interface IControlador<T> {
    public boolean cadastra(T item);
    
    public boolean remove(T item);
    
    public boolean modifica(T old, T novo);
    
    public ArrayList<T> getLista ();
    
    public void exibeTela();
    
}
