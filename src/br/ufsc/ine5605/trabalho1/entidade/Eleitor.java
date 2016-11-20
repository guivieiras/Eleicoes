package br.ufsc.ine5605.trabalho1.entidade;

import java.io.Serializable;

public class Eleitor implements Serializable{

    private int zonaEleitoral;
    private int secaoEleitoral;
    private long titulo;
    private String nome;
    private Cidade cidade;
    
    private boolean jaVotou = false;

    public Eleitor(int zonaEleitoral, int secaoEleitoral, long titulo, String nome, Cidade cidade) {
        this.zonaEleitoral = zonaEleitoral;
        this.secaoEleitoral = secaoEleitoral;
        this.titulo = titulo;
        this.nome = nome;
        this.cidade = cidade;
    }

    public boolean jaVotou() {
        return jaVotou;
    }

    public void votar() {
        this.jaVotou = true;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public int getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(int zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public int getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public void setSecaoEleitoral(int secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }

    public long getTitulo() {
        return this.titulo;
    }

    public void setTitulo(long titulo) {
        this.titulo = titulo;

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;

    }
    
    @Override
    public boolean equals(Object p) {
        if (p instanceof Eleitor) {
            Eleitor p1 = (Eleitor) p;
            return p1.getTitulo()== titulo;
        }
        return false;
    }
        
}
