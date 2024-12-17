package com.upt.lp.rest_api5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upt.lp.rest_api5.model.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
	List<Doacao> findAllByIdRequerenteOrIdDoador(Long idRequerente, Long idDoador);
<<<<<<< HEAD
	List<Doacao> findByIdDoador(Long idDoador);
	List<Doacao> findAllByIdRequerente(Long idRequerente);
=======
>>>>>>> 390dc94 (commit of donations part and inherent methods)
}

