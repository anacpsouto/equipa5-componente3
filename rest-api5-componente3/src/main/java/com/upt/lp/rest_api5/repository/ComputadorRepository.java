package com.upt.lp.rest_api5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.model.EstadoEquipamento;

@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Long> {
	
	List<Computador> findByEstado(EstadoEquipamento estado);

}
