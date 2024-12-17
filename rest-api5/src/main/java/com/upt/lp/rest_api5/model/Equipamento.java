package com.upt.lp.rest_api5.model;

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
    private Long id;

    private String nome;
    private String ano;
    private String brand; //criar tabela separada 
    private ConservationStatus conservationStatus; //fazer tabela enum

    // Novo campo para armazenar o userID
    private Long userID;

    public Equipamento() {
    }

    public Equipamento(String nome, String ano, String brand, ConservationStatus conservationStatus, Long userID) {
        this.nome = nome;
        this.ano = ano;
        this.brand = brand;
        this.conservationStatus = conservationStatus;
        this.userID = userID; // Atribuindo o userID no construtor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ConservationStatus getEstadoConservacao() {
        return conservationStatus;
    }

    public void setEstadoConservacao(ConservationStatus conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    // Getter e Setter para userID
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Equipamento: " +
               "\nid=" + id + 
               ", \nnome=" + nome + 
               ", \nano=" + ano + 
               ", \nmarca=" + brand + 
               ", \nestadoConservacao=" + conservationStatus +
               ", \nuserID=" + userID;
    }
}
