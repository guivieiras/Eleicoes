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
public class PartidoDuplicado extends Exception {

    public PartidoDuplicado() {
        super("partido de mesmo nome já cadastrado.");
    }
}
