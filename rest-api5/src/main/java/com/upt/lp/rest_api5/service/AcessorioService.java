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
	
	/*
	 * public Acessorio updateAcessorio(Long id, Acessorio acessorioDetails) {
	 * Acessorio acessorio = acessorioRepository.findById(id).orElse(null); if
	 * (acessorio != null) { acessorio.setTipo(acessorioDetails.getTipo());
	 * acessorio.setInformacao(acessorioDetails.getInformacao()); return
	 * acessorioRepository.save(acessorio); }
	 * 
	 * return null; }
	 */
	
	public Acessorio updateAcessorio(Long id, Acessorio acessorio) {
	    Optional<Acessorio> existingAcessorio = acessorioRepository.findById(id);
	    if (existingAcessorio.isPresent()) {
	        Acessorio updated = existingAcessorio.get();
	        updated.setTipo(acessorio.getTipo());
	        updated.setInformacao(acessorio.getInformacao());
	        updated.setNome(acessorio.getNome());
	        updated.setAno(acessorio.getAno());
	        updated.setBrand(acessorio.getBrand());
	        updated.setEstadoConservacao(acessorio.getEstadoConservacao());
	        return acessorioRepository.save(updated);
	    } else {
	        throw new RuntimeException("Acessório com ID " + id + " não encontrado.");
	    }
	}
	
	public void deleteAcessorio(Long id) {
		if (acessorioRepository.existsById(id) ) {
			acessorioRepository.deleteById(id);
		} else {
			throw new RuntimeException ("Acessorio not found with id: " + id);
		}
	}
	
	
}

