package br.ufsc.ine5605.trabalho1.entidade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import br.ufsc.ine5605.trabalho1.apresentacao.TelaVotacao;

public class Urna {

	private HashMap<Candidato,Integer> totalDeVotosPorCandidato;
	private int secaoEleitoral;
	private int zonaEleitoral;
	private Cidade cidade;
	private int numeroEleitores;
	private int votosEfetuados;
	private int votosInvalidos;

	public Urna(int numeroEleitores, int secaoEleitoral, int zonaEleitoral, Cidade cidade, ArrayList<Candidato> candidatos) {
		votosEfetuados = 0;
		votosInvalidos = 0;
		setSecaoEleitoral(secaoEleitoral);
		setNumeroEleitores(numeroEleitores);
		setZonaEleitoral(zonaEleitoral);
		setCidade(cidade);
		totalDeVotosPorCandidato=new HashMap<>();
		for(Candidato candidato: candidatos){
			totalDeVotosPorCandidato.put(candidato, 0);
		}
	}
	public int getVotosInvalidos(){
		return (this.votosInvalidos - votosEfetuados + (2*numeroEleitores));
	}
	private void setZonaEleitoral(int zonaEleitoral) {
		this.zonaEleitoral=zonaEleitoral;
	}
	private void setCidade(Cidade cidade) {
		this.cidade=cidade;
	}
	private void setNumeroEleitores(int numeroEleitores) {
		this.numeroEleitores=numeroEleitores;
	}
	private void setSecaoEleitoral(int secaoEleitoral) {
		this.secaoEleitoral=secaoEleitoral;
	}
	public void contabilizaVoto(int codigoPrefeito, int codigoVereador) {
		Candidato prefeito = getCandidatoPorCodigo(codigoPrefeito);
		Candidato vereador = getCandidatoPorCodigo(codigoVereador);
		votosEfetuados++;
		if(prefeito!=null){
			totalDeVotosPorCandidato.put(prefeito, totalDeVotosPorCandidato.get(prefeito)+1);
		}else{
			votosEfetuados++;
			votosInvalidos++;
		}
		votosEfetuados++;
		if(vereador!=null){
			totalDeVotosPorCandidato.put(vereador, totalDeVotosPorCandidato.get(vereador)+1);
		}else{
			votosInvalidos++;	
		}
	}
	public HashMap<Candidato,Integer> getTotalDeVotosPorCandidato() {
		return totalDeVotosPorCandidato;
	}
	public Cidade getCidade() {
		return cidade;
	}
    public Candidato getCandidatoPorCodigo(int codigo){
    	for(Entry<Candidato, Integer> entry : totalDeVotosPorCandidato.entrySet()) {
    	    Candidato key = entry.getKey();
    	    if(key.getNumero()==codigo){
    	    	return key;
    	    }
    	}
    	return null;
    }
    public boolean exibeTelaVotacao() {
    	if(numeroEleitores*2<=votosEfetuados){
    		TelaVotacao tela = new TelaVotacao(this);
    		tela.setVisible(true);
    		return true;
    	}
    	return false;
    }

    public boolean verificaEleitor(Eleitor eleitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}