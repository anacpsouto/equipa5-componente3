package com.upt.lp.rest_api5.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upt.lp.rest_api5.model.Utilizador;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    // Aqui você pode adicionar consultas customizadas se necessário, mas o JpaRepository já fornece métodos básicos como findAll, findById, etc.
	
	Utilizador findByEmail(String email); // Método para buscar pelo email
}
