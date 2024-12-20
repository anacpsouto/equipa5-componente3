package com.upt.lp.rest_api5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Computador extends Equipamento {

    public enum ComputerType {
        DESKTOP,
        PORTATIL
    }

    public enum RamType {
        GB_8,
        GB_16,
        GB_32,
        GB_64
    }

    @Enumerated(EnumType.STRING)
    private ComputerType computerType;

    @Enumerated(EnumType.STRING)
    private RamType ram;

    private String disco;
    private String processador;

    public Computador() {
    }

	/*
	 * public Computador(ComputerType computerType, RamType ram, String disco,
	 * String processador) { this.computerType = computerType; this.ram = ram;
	 * this.disco = disco; this.processador = processador; }
	 */
    
 // Construtor que recebe todos os parâmetros
    public Computador(String nome, String ano, String brand, EstadoConservacao estadoConservacao, EstadoEquipamento estadoEquipamento, ComputerType computerType,
                      RamType ram, String disco, String processador) {
        super(nome, ano, brand, estadoConservacao, estadoEquipamento);  // Chama o construtor da classe mãe Equipamento
        this.computerType = computerType;
        this.ram = ram;
        this.disco = disco;
        this.processador = processador;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public RamType getRam() {
        return ram;
    }

    public void setRam(RamType ram) {
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
        return "Computador [computerType=" + computerType + 
               ", ram=" + ram + 
               ", disco=" + disco + 
               ", processador=" + processador + 
               ", toString()=" + super.toString() + "]";
    }
}
