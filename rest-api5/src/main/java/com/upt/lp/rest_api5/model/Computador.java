package com.upt.lp.rest_api5.model;

import jakarta.persistence.Entity;

@Entity
public class Computador extends Equipamento{
	
	private String tipoComputador;
	private String ram;
	private String disco;
	private String processador;
	
	public Computador() {
	}
	
	/*
	 * public Computador(String nome, String ano, String modelo, String
	 * estadoConservacao, String tipoComputador, String ram, String disco, String
	 * processador) { super(nome, ano, modelo, estadoConservacao);
	 * this.tipoComputador = tipoComputador; this.ram = ram; this.disco = disco;
	 * this.processador = processador; }
	 */
	
	public String getTipo() {
		return tipoComputador;
	}

	public void setType(String tipoComputador) {
		this.tipoComputador = tipoComputador;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDisco() {
		return disco;
	}

	public void setDisco(String disco) {
		this.disco = disco;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	@Override
	public String toString() {
		return "Computador [tipoComputador=" + tipoComputador + 
				", \nram=" + ram + 
				", \ndisco=" + disco + 
				", \nprocessador=" + processador + 
				", \ntoString()=" + super.toString() + "]";
	}

	
	
	
	

}
