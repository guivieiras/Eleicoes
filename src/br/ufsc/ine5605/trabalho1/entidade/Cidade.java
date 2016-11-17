package br.ufsc.ine5605.trabalho1.entidade;

public class Cidade {

    private String nome;
    private int codigo;

    public Cidade(String nome) {
        this.nome = nome;
        this.codigo = hashCode();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }
    
    @Override
    public String toString(){
        return nome;
    }

}
