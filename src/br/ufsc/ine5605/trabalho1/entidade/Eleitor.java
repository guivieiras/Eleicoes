package br.ufsc.ine5605.trabalho1.entidade;

public class Eleitor {

    private int zonaEleitoral;
    private int secaoEleitoral;
    private long titulo;
    private String nome;
    private Cidade cidade;

    public Eleitor(int zonaEleitoral, int secaoEleitoral, long titulo, String nome, Cidade cidade) {
        this.zonaEleitoral = zonaEleitoral;
        this.secaoEleitoral = secaoEleitoral;
        this.titulo = titulo;
        this.nome = nome;
        this.cidade = cidade;
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

}
