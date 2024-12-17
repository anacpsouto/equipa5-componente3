package com.upt.lp.rest_api5.model;

import jakarta.persistence.Entity;

@Entity
public class Computador extends Equipamento{
	
	//validar e tipificar para nao deixar que coloquem qualquer coisa 
	private String computerType; //1 == laptop 2 == desktop
	private String ram; //enum 
	private String disco;//mudar para capacidade, limitar e colcoar como int 
	private String processador;//campo aberto ou tabela (assumir que podia ter melhorado)
	
	public Computador() {
	}
	
	/*
	 * public Computador(String nome, String ano, String modelo, String
	 * estadoConservacao, String tipoComputador, String ram, String disco, String
	 * processador) { super(nome, ano, modelo, estadoConservacao);
	 * this.tipoComputador = tipoComputador; this.ram = ram; this.disco = disco;
	 * this.processador = processador; }
	 */
	
	public String getType() {
		return computerType;
	}

	public void setType(String computerType) {
		this.computerType = computerType;
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
		return "Computador [tipoComputador=" + computerType + 
				", \nram=" + ram + 
				", \ndisco=" + disco + 
				", \nprocessador=" + processador + 
				", \ntoString()=" + super.toString() + "]";
	}

	
	
	
	

}
