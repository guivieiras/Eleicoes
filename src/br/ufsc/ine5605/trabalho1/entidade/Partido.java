package br.ufsc.ine5605.trabalho1.entidade;

import java.io.Serializable;

public class Partido implements Serializable {

    private String nome;
    private String sigla;
    private int codigo;

    public Partido(String nome, String sigla) {
        setSigla(sigla);
        setNome(nome);
        this.codigo = hashCode();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCodigo() {
        return this.codigo;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object p) {
        if (p instanceof Partido) {
            Partido p1 = (Partido) p;
            return p1.getCodigo() == codigo;
        }
        return false;
    }

}
