package com.upt.lp.rest_api5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private String nome;
	private String ano;
	private String brand;
	private String estadoConservacao;

	public Equipamento() {
	}

	public Equipamento(String nome, String ano, String brand, String estadoConservacao) {
		this.nome = nome;
		this.ano = ano;
		this.brand = brand;
		this.estadoConservacao = estadoConservacao;
	}

	/*
	 * public Equipamento(String nome, String ano, String modelo, String
	 * estadoConservacao) { this.nome = nome; this.ano = ano; this.modelo = modelo;
	 * this.estadoConservacao = estadoConservacao; }
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEstadoConservacao() {
		return estadoConservacao;
	}

	public void setEstadoConservacao(String estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}

	@Override
	public String toString() {
		return "Equipamento: "+ 
				"\nid=" + id + 
				", \nnome=" + nome + 
				", \nano=" + ano + 
				", \nmarca=" + brand + 
				", \nestadoConservacao=" + estadoConservacao;
	}

}
