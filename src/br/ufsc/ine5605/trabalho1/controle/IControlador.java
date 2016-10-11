/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.controle;

import java.util.ArrayList;

/**
 *
 * @author The Gui
 */
public interface IControlador<T> {
    public boolean cadastra(T classe);
    
    public ArrayList<T> getArray ();
    
}
