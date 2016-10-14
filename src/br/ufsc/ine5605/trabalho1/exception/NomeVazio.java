package br.ufsc.ine5605.trabalho1.exception;

public class NomeVazio extends Exception{
    public NomeVazio()
    {
        super("Nome não pode ser vazio.");
    }
    
}
