package com.upt.lp.rest_api5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    // Relacionamento com a classe Brand
    @ManyToOne
    private Brand brand; // Associa a marca com a tabela de marcas

    @Enumerated(EnumType.STRING)
    private EstadoConservacao estadoConservacao; // Usando o ENUM para estado de conservação

    @Enumerated(EnumType.STRING) 
    private EstadoEquipamento estadoEquipamento = EstadoEquipamento.PENDENTE;

    private Long userID;

    public Equipamento() {
    }

    public Equipamento(String nome, String ano, Brand brand, EstadoConservacao estadoConservacao, Long userID, EstadoEquipamento estadoEquipamento) {
        this.nome = nome;
        this.ano = ano;
        this.brand = brand;
        this.estadoConservacao = estadoConservacao;
        this.userID = userID;
        this.estadoEquipamento = estadoEquipamento;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public EstadoEquipamento getEstadoEquipamento() {
        return estadoEquipamento;
    }

    public void setEstadoEquipamento(EstadoEquipamento estadoEquipamento) {
        this.estadoEquipamento = estadoEquipamento;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Equipamento [id=" + id + 
               ", nome=" + nome + 
               ", ano=" + ano + 
               ", brand=" + brand + 
               ", estadoConservacao=" + estadoConservacao + 
               ", estadoEquipamento=" + estadoEquipamento + 
               ", userID=" + userID + "]";
    }
}
