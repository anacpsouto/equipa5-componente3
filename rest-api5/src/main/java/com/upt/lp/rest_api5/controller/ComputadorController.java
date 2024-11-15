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

import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.service.ComputadorService;

@RestController
@RequestMapping("/api/computadores")
public class ComputadorController {
	
	@Autowired
	private ComputadorService computadorService;
	
	@GetMapping
	public List<Computador> getAllComputadores() {
		return computadorService.getAllComputadores();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Computador> getComputadorById(@PathVariable Long id){
		Optional <Computador> computador = computadorService.getComputadorById(id);
		return computador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}
	
	@PostMapping
	public Computador createComputador (@RequestBody Computador computador) {
		System.out.println("Criando computador: " + computador);
		return computadorService.createComputador(computador);
	}
	
	@PutMapping("/{id}")
	public Computador updateComputador(@PathVariable Long id, @RequestBody Computador computador) {
		return computadorService.updateComputador(id, computador);
	}
	
	@DeleteMapping("/{id}")
	public String deleteAComputador(@PathVariable Long id) {
		computadorService.deleteComputador(id);
		return "Computer deleted sucessfully";
	}
	
}
	
