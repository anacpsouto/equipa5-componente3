package com.upt.lp.rest_api5.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataInicio; // fazer regra para a doação ser recusada automaticamente depois de algum tempo

    private LocalDate dataFim;

	/*
	 * @Column(nullable = false) private String estadoDoacao; // "PENDENTE",
	 * "TERMINADA", "CANCELADA" -> fazer enum !!!!!!!!!!!!!!!
	 */
    
    @Enumerated(EnumType.STRING) // Armazena o enum como uma string no banco de dados
    @Column(nullable = false)
    private EstadoDoacao estadoDoacao;
    
    @Column(nullable = false)
    private Long idRequerente;

    @Column(nullable = false)
    private Long idDoador;

    @Column(nullable = false)
    private Long idEquipamento;
    
    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

	/*
	 * public String getEstadoDoacao() { return estadoDoacao; }
	 */

	/*
	 * public void setEstadoDoacao(String estadoDoacao) { this.estadoDoacao =
	 * estadoDoacao; }
	 */
    
    public EstadoDoacao getEstadoDoacao() {
        return estadoDoacao;
    }

    public void setEstadoDoacao(EstadoDoacao estadoDoacao) {
        this.estadoDoacao = estadoDoacao;
    }

    public Long getIdRequerente() {
        return idRequerente;
    }

    public void setIdRequerente(Long idRequerente) {
        this.idRequerente = idRequerente;
    }

    public Long getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(Long idDoador) {
        this.idDoador = idDoador;
    }

    public Long getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Long idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

	/*
	 * @Override public String toString() { return "Doacao [id=" + id +
	 * ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", estadoDoacao=" +
	 * estadoDoacao + ", idRequerente=" + idRequerente + ", idDoador=" + idDoador +
	 * ", idEquipamento=" + idEquipamento + "]"; }
	 */
    
    @Override
    public String toString() {
        return "Doacao [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", estadoDoacao="
                + estadoDoacao + ", idRequerente=" + idRequerente + ", idDoador=" + idDoador + ", idEquipamento="
                + idEquipamento + "]";
    }

}

