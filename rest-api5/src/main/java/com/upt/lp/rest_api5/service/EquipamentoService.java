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

    public List<Equipamento> getAllEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        equipamentos.addAll(computadorRepository.findAll());
        equipamentos.addAll(acessorioRepository.findAll());
        return equipamentos;
    }

    public Equipamento getEquipamentoById(Long id) {
        Optional <Computador> computador = computadorRepository.findById(id);
        if(computador.isPresent())
            return computador.get();
        Optional <Acessorio> acessorio = acessorioRepository.findById(id);
        return acessorio.orElse(null);
    }

    public Equipamento createEquipamento(Equipamento equipamento) {
        // Atribui o userID ao equipamento
        if (equipamento instanceof Computador) {
            return computadorRepository.save((Computador) equipamento);
        } else if (equipamento instanceof Acessorio) {
            return acessorioRepository.save((Acessorio) equipamento);
        }
        throw new IllegalArgumentException("Unknown equipment type");
    }

    public void deleteEquipamento(Long id) {
        computadorRepository.deleteById(id);
        acessorioRepository.deleteById(id);
    }

    public Equipamento updateEquipamento(Long id, Equipamento equipamento) {
        if (equipamento instanceof Computador) {
            Computador existingComputador = computadorRepository.findById(id).orElse(null);
            if (existingComputador != null) {
                existingComputador.setType(((Computador) equipamento).getType());
                existingComputador.setRam(((Computador) equipamento).getRam());
                existingComputador.setUserID(equipamento.getUserID()); // Atualiza o userID
                return computadorRepository.save(existingComputador);
            }
        } else if (equipamento instanceof Acessorio) {
            Acessorio existingAcessorio = acessorioRepository.findById(id).orElse(null);
            if (existingAcessorio != null) {
                existingAcessorio.setTipo(((Acessorio) equipamento).getTipo());
                existingAcessorio.setInformacao(((Acessorio) equipamento).getInformacao());
                existingAcessorio.setUserID(equipamento.getUserID()); // Atualiza o userID
                return acessorioRepository.save(existingAcessorio);
            }
        }
        return null;
    }
<<<<<<< HEAD

=======
>>>>>>> 390dc94 (commit of donations part and inherent methods)
}
