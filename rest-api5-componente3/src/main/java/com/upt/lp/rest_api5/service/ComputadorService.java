package com.upt.lp.rest_api5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.model.EstadoEquipamento;
import com.upt.lp.rest_api5.repository.ComputadorRepository;

@Service
public class ComputadorService {

    private final ComputadorRepository computadorRepository;

    @Autowired
    public ComputadorService(ComputadorRepository computadorRepository) {
        this.computadorRepository = computadorRepository;
    }

    // Retorna todos os computadores
    public List<Computador> getAllComputadores() {
        return computadorRepository.findAll();
    }

    // Retorna um computador específico pelo ID
    public Optional<Computador> getComputadorById(Long id) {
        return computadorRepository.findById(id);
    }

    // Cria um novo computador
    public Computador createComputador(Computador computador) {
		System.out.println("Salvando computador no banco de dados: " + computador);
		return computadorRepository.save(computador);
	}

    // Atualiza um computador existente
    public Computador updateComputador(Long id, Computador computador) {
        Optional<Computador> existingComputador = computadorRepository.findById(id);
        if (existingComputador.isPresent()) {
            Computador updated = existingComputador.get();
            updated.setComputerType(computador.getComputerType());
            updated.setRam(computador.getRam());
            updated.setDisco(computador.getDisco());
            updated.setProcessador(computador.getProcessador());
            updated.setNome(computador.getNome());
            updated.setAno(computador.getAno());
            updated.setBrand(computador.getBrand()); // Atualiza a marca
            updated.setEstadoConservacao(computador.getEstadoConservacao()); // Atualiza o estado de conservação
            updated.setEstadoEquipamento(computador.getEstadoEquipamento()); // Atualiza o estado do equipamento
            return computadorRepository.save(updated);
        } else {
            throw new RuntimeException("Computador com ID " + id + " não encontrado.");
        }
    }

    // Deleta um computador
    public void deleteComputador(Long id) {
        if (computadorRepository.existsById(id)) {
            computadorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Computador não encontrado com o ID: " + id);
        }
    }

	/*
	 * // Busca computadores com um estado específico public List<Computador>
	 * findByEstado(EstadoEquipamento estado) { return
	 * computadorRepository.findByEstado(estado); // Certifique-se de que o
	 * repositório possui esse método }
	 */

    // Atualiza um computador (admin)
    public Computador updateComputadorAdmin(Computador computador) {
        return computadorRepository.save(computador); // Atualiza no banco de dados
    }
}
