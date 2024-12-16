package com.upt.lp.rest_api5.model;

import jakarta.persistence.Entity;

@Entity
public class Acessorio extends Equipamento {
	
	private String tipoAcessorio;
	private String informacao;
	
	public Acessorio() {
		
	}
	
	// String nome, String ano, String modelo, String estadoConservacao, 
	
	/*
	 * public Acessorio(String nome, String ano, String modelo, String
	 * estadoConservacao,String tipoAcessorio, String informacao) { super(nome,ano,
	 * modelo, estadoConservacao); this.tipoAcessorio = tipoAcessorio;
	 * this.informacao = informacao; }
	 */

	
	public String getTipo() {
		return tipoAcessorio;
	}
	public void setTipo(String tipoAcessorio) {
		this.tipoAcessorio = tipoAcessorio;
	}
	public String getInformacao() {
		return informacao;
	}
	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	@Override
	public String toString() {
		return "Acessorio: " +
				"\nTipoAcessorio = " + tipoAcessorio + 
				"\nInformação = " + informacao + 
				"\ntoString() = " + super.toString();
	}
	
	

}
