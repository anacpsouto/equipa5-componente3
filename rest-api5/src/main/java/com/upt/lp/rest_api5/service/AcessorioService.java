package com.upt.lp.rest_api5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.repository.AcessorioRepository;

@Service
public class AcessorioService {
	
	private final AcessorioRepository acessorioRepository;
	
	@Autowired
	public AcessorioService (AcessorioRepository acessorioRepository) {
		this.acessorioRepository = acessorioRepository;
	}
	
	public List<Acessorio> getAllAcessorios() {
		return acessorioRepository.findAll();
	}
	
	public Optional<Acessorio> getAcessorioById(Long id){
		return acessorioRepository.findById(id);
	}

	public Acessorio createAcessorio(Acessorio acessorio) {
		return acessorioRepository.save(acessorio);
		
		/*
		 * try { return acessorioRepository.save(acessorio); } catch (Exception e) {
		 * System.err.println("Erro ao salvar computador: " + e.getMessage());
		 * e.printStackTrace(); return null; }
		 */
	}
	
	public Acessorio updateAcessorio(Long id, Acessorio acessorioDetails) {
		Acessorio acessorio = acessorioRepository.findById(id).orElse(null);
		if (acessorio != null) {
			acessorio.setTipo(acessorioDetails.getTipo());
			acessorio.setInformacao(acessorioDetails.getInformacao());
			return acessorioRepository.save(acessorio);
		}
		
		return null;
	}
	
	public void deleteAcessorio(Long id) {
		if (acessorioRepository.existsById(id) ) {
			acessorioRepository.deleteById(id);
		} else {
			throw new RuntimeException ("Acessorio not found with id: " + id);
		}
	}
	
	
}

