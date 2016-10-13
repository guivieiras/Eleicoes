/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.entidade;

/**
 *
 * @author Guilherme
 */
public class Tupla <T,E>
{
    public T value1;
    public E value2;
    
    public Tupla(T v1, E v2)
    {
        value1 =v1;
        value2=v2;
    }
}
