package br.ufsc.ine5605.trabalho1.entidade;

/**
 *  
 *  @author 10349509913 
 * 
 */
public class Eleitor {

	private long titulo;

	private String nome;

	public Eleitor(long titulo, String nome) {
            this.titulo = titulo;
            this.nome = nome;
	}

	public long getTitulo() {
		return titulo;
	}

	public void setTitulo(long titulo) {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {

	}

}
