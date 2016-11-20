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
public class TurnoInvalido extends Exception {
    public TurnoInvalido() {
        super("o turno dessa cidade não está de acordo com o da urna.");
    }
}
