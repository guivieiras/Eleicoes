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
public class CidadeDuplicada extends Exception {
    public CidadeDuplicada() {
        super("cidade de mesmo nome já cadastrada.");
    }
}
