package com.upt.lp.rest_api5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.repository.ComputadorRepository;

@Service
public class ComputadorService {
	
	private final ComputadorRepository computadorRepository;
	
	@Autowired
	public ComputadorService (ComputadorRepository computadorRepository) {
		this.computadorRepository = computadorRepository;
	}
	
	public List<Computador> getAllComputadores() {
		return computadorRepository.findAll();
	}
	
	public Optional<Computador> getComputadorById(Long id){
		return computadorRepository.findById(id);
	}

	public Computador createComputador(Computador computador) {
		System.out.println("Salvando computador no banco de dados: " + computador);
		return computadorRepository.save(computador);
	}
	
	public Computador updateComputador(Long id, Computador computadorDetails) {
		Computador computador = computadorRepository.findById(id).orElse(null);
		if (computador != null) {
			computador.setTipo(computadorDetails.getTipo());
			computador.setRam(computadorDetails.getRam());
			computador.setDisco(computadorDetails.getDisco());
			computador.setProcessador(computadorDetails.getProcessador());
			return computadorRepository.save(computador);
		}
		
		return null;
	}
	
	public void deleteComputador(Long id) {
		if (computadorRepository.existsById(id) ) {
			computadorRepository.deleteById(id);
		} else {
			throw new RuntimeException ("Computador not found with id: " + id);
		}
	}
	
	
}

