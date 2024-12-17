package com.upt.lp.rest_api5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.service.AcessorioService;

@RestController
@RequestMapping("/api/acessorios")
public class AcessorioController {
	
	@Autowired
	private AcessorioService acessorioService;
	
	@GetMapping
	public List<Acessorio> getAllAcessorios() {
		return acessorioService.getAllAcessorios();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Acessorio> getAcessorioById(@PathVariable Long id){
		Optional <Acessorio> acessorio = acessorioService.getAcessorioById(id);
		return acessorio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}
	
	@PostMapping
	public Acessorio createAcessorio (@RequestBody Acessorio acessorio) {
		return acessorioService.createAcessorio(acessorio);
	}
	
	/*
	 * @PutMapping("/{id}") public Acessorio updateAcessorio(@PathVariable Long
	 * id, @RequestBody Acessorio acessorio) { return
	 * acessorioService.updateAcessorio(id, acessorio); }
	 */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAcessorio(@PathVariable Long id, @RequestBody Acessorio acessorio) {
	    Optional<Acessorio> existingAcessorio = acessorioService.getAcessorioById(id);
	    if (existingAcessorio.isPresent()) {
	        Acessorio updated = acessorioService.updateAcessorio(id, acessorio);
	        return ResponseEntity.ok(updated);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("/{id}")
	public String deleteAcessorio(@PathVariable Long id) {
		acessorioService.deleteAcessorio(id);
		return "Acessory deleted sucessfully";
	}
	
}