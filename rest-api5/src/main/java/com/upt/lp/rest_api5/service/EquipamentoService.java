package com.upt.lp.rest_api5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.model.Equipamento;
import com.upt.lp.rest_api5.repository.AcessorioRepository;
import com.upt.lp.rest_api5.repository.ComputadorRepository;

@Service
public class EquipamentoService {

    @Autowired
    private ComputadorRepository computadorRepository;

    @Autowired
    private AcessorioRepository acessorioRepository;

    // Retorna todos os equipamentos (incluindo computadores e acessórios)
    public List<Equipamento> getAllEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        equipamentos.addAll(computadorRepository.findAll());
        equipamentos.addAll(acessorioRepository.findAll());
        return equipamentos;
    }

    // Retorna um equipamento específico baseado no ID
    public Equipamento getEquipamentoById(Long id) {
        Optional<Computador> computador = computadorRepository.findById(id);
        if (computador.isPresent()) {
            return computador.get();
        }
        Optional<Acessorio> acessorio = acessorioRepository.findById(id);
        return acessorio.orElse(null);
    }

    // Cria um novo equipamento, seja computador ou acessório
    public Equipamento createEquipamento(Equipamento equipamento) {
        // Verifica o tipo do equipamento e atribui os valores adequados
        if (equipamento instanceof Computador) {
            Computador computador = (Computador) equipamento;
            // Atribui valores para brand, estadoConservacao e outros, se necessário
            return computadorRepository.save(computador);
        } else if (equipamento instanceof Acessorio) {
            Acessorio acessorio = (Acessorio) equipamento;
            return acessorioRepository.save(acessorio);
        }
        throw new IllegalArgumentException("Unknown equipment type");
    }

    // Deleta um equipamento
    public void deleteEquipamento(Long id) {
        // Verifica se é Computador ou Acessório e deleta
        computadorRepository.deleteById(id);
        acessorioRepository.deleteById(id);
    }

    // Atualiza um equipamento existente
    public Equipamento updateEquipamento(Long id, Equipamento equipamento) {
        if (equipamento instanceof Computador) {
            Computador existingComputador = computadorRepository.findById(id).orElse(null);
            if (existingComputador != null) {
                Computador computadorToUpdate = (Computador) equipamento;
                // Atualiza os campos conforme necessário
                existingComputador.setComputerType(computadorToUpdate.getComputerType());
                existingComputador.setRam(computadorToUpdate.getRam());
                existingComputador.setDisco(computadorToUpdate.getDisco());
                existingComputador.setProcessador(computadorToUpdate.getProcessador());
                //existingComputador.setUserID(equipamento.getUserID());
                existingComputador.setEstadoEquipamento(equipamento.getEstadoEquipamento());
                existingComputador.setEstadoConservacao(equipamento.getEstadoConservacao());
                existingComputador.setBrand(computadorToUpdate.getBrand()); // Atualiza a marca
                return computadorRepository.save(existingComputador);
            }
        } else if (equipamento instanceof Acessorio) {
            Acessorio existingAcessorio = acessorioRepository.findById(id).orElse(null);
            if (existingAcessorio != null) {
                Acessorio acessorioToUpdate = (Acessorio) equipamento;
                // Atualiza os campos de Acessorio
                existingAcessorio.setTipo(acessorioToUpdate.getTipo());
                existingAcessorio.setInformacao(acessorioToUpdate.getInformacao());
                //existingAcessorio.setUserID(equipamento.getUserID());
                existingAcessorio.setEstadoEquipamento(equipamento.getEstadoEquipamento());
                return acessorioRepository.save(existingAcessorio);
            }
        }
        return null;
    }
}
