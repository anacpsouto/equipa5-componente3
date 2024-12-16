package com.upt.lp.rest_api5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.model.EstadoEquipamento;
import com.upt.lp.rest_api5.repository.AcessorioRepository;

@Service
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;

    @Autowired
    public AcessorioService(AcessorioRepository acessorioRepository) {
        this.acessorioRepository = acessorioRepository;
    }

    // Retorna todos os acessórios
    public List<Acessorio> getAllAcessorios() {
        return acessorioRepository.findAll();
    }

    // Retorna um acessório pelo ID
    public Optional<Acessorio> getAcessorioById(Long id) {
        return acessorioRepository.findById(id);
    }

    // Cria um novo acessório
    public Acessorio createAcessorio(Acessorio acessorio) {
        System.out.println("Salvando acessório no banco de dados: " + acessorio);
        return acessorioRepository.save(acessorio);
    }

    // Atualiza um acessório existente
    public Acessorio updateAcessorio(Long id, Acessorio acessorio) {
        Optional<Acessorio> existingAcessorio = acessorioRepository.findById(id);
        
        if (existingAcessorio.isPresent()) {
            Acessorio updated = existingAcessorio.get();
            
            // Atualiza os campos do acessório
            updated.setTipo(acessorio.getTipo()); // Atualiza o tipo do acessório
            updated.setInformacao(acessorio.getInformacao()); // Atualiza a informação do acessório
            updated.setNome(acessorio.getNome()); // Atualiza o nome do acessório
            updated.setAno(acessorio.getAno()); // Atualiza o ano do acessório
            updated.setBrand(acessorio.getBrand()); // Atualiza a marca do acessório
            updated.setEstadoConservacao(acessorio.getEstadoConservacao()); // Atualiza o estado de conservação
            updated.setEstadoEquipamento(acessorio.getEstadoEquipamento()); // Atualiza o estado do equipamento
            
            return acessorioRepository.save(updated);
        } else {
            throw new RuntimeException("Acessório com ID " + id + " não encontrado.");
        }
    }

    // Deleta um acessório pelo ID
    public void deleteAcessorio(Long id) {
        if (acessorioRepository.existsById(id)) {
            acessorioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Acessório não encontrado com ID: " + id);
        }
    }

	/*
	 * // Encontra acessórios pelo estado do equipamento public List<Acessorio>
	 * findByEstado(EstadoEquipamento estado) { return
	 * acessorioRepository.findByEstado(estado); // Repositório para busca }
	 */

    // Atualiza um acessório administrativamente
    public Acessorio updateAcessorioAdmin(Acessorio acessorio) {
        return acessorioRepository.save(acessorio); // Atualiza no banco de dados
    }
}
