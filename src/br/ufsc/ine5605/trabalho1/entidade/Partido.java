package br.ufsc.ine5605.trabalho1.entidade;

public class Partido {

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
    
    public int getCodigo(){
        return this.codigo;
    }

}
