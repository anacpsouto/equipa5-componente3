package com.upt.lp.rest_api5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upt.lp.rest_api5.model.Equipamento;
import com.upt.lp.rest_api5.service.EquipamentoService;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@GetMapping
	public List<Equipamento> getAllEquipamentos() {
		return equipamentoService.getAllEquipamentos();
	}
	
	@GetMapping("/{id}")
	public Equipamento getEquipamentoById(@PathVariable Long id) {
		return equipamentoService.getEquipamentoById(id);
	}
	
	@PostMapping
	public ResponseEntity<Equipamento> createEquipamento(@RequestBody Equipamento equipamento) {
		//return equipamentoService.createEquipamento(equipamento);
		Equipamento savedEquipamento = equipamentoService.createEquipamento(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipamento);
	}
	
	@PutMapping("/{id}")
	public Equipamento updateEquipamento (@PathVariable Long id, @RequestBody Equipamento equipamento) {
		return equipamentoService.updateEquipamento(id, equipamento);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEquipamento(@PathVariable Long id) {
		equipamentoService.deleteEquipamento(id);
		return "Equiapment deleted sucessfully";
	}
		
	}