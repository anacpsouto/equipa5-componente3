package com.upt.lp.rest_api5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upt.lp.rest_api5.model.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
	List<Doacao> findAllByIdRequerenteOrIdDoador(Long idRequerente, Long idDoador);
	List<Doacao> findByIdDoador(Long idDoador);
	List<Doacao> findAllByIdRequerente(Long idRequerente);
}

