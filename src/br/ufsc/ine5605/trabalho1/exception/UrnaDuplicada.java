/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.exception;

/**
 *
 * @author Guilherme
 */
public class UrnaDuplicada extends Exception {

    public UrnaDuplicada() {
        super("urna de mesma cidade, seção e zona já cadastrada.");
    }
}
