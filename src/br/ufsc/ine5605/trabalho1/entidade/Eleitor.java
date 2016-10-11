package br.ufsc.ine5605.trabalho1.entidade;

/**
 *
 * @author 10349509913
 *
 */
public class Eleitor {

    private int zonaEleitoral;
    private int secaoEleitoral;
    private long titulo;
    private String nome;

    public Eleitor(int zonaEleitoral, int secaoEleitoral, long titulo, String nome) {
        this.zonaEleitoral = zonaEleitoral;
        this.secaoEleitoral = secaoEleitoral;
        this.titulo = titulo;
        this.nome = nome;

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
