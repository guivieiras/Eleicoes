package br.ufsc.ine5605.trabalho1.controle;

import java.util.ArrayList;

public interface IControlador<T> {
    public boolean cadastra(T item) throws Exception;
    
    public boolean remove(T item) throws Exception;
    
    public boolean modifica(T old, T novo) throws Exception;
    
    public ArrayList<T> getLista ();
    
    public void exibeTela();
}
