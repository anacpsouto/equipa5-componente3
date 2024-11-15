package com.upt.lp.rest_api5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upt.lp.rest_api5.model.Acessorio;

@Repository
public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {

}
