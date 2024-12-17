package com.upt.lp.rest_api5.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.lp.rest_api5.model.Doacao;
import com.upt.lp.rest_api5.model.EstadoDoacao;
import com.upt.lp.rest_api5.repository.DoacaoRepository;

@Service
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

	/*
	 * public Doacao registrarPedidoDoacao(Long idRequerente, Long idDoador, Long
	 * idEquipamento) { Doacao doacao = new Doacao();
	 * doacao.setDataInicio(LocalDate.now()); doacao.setEstadoDoacao("PENDENTE");
	 * doacao.setIdRequerente(idRequerente); doacao.setIdDoador(idDoador);
	 * doacao.setIdEquipamento(idEquipamento);
	 * 
	 * return doacaoRepository.save(doacao); }
	 */
    
    public Doacao registrarPedidoDoacao(Long idRequerente, Long idDoador, Long idEquipamento) {
        Doacao doacao = new Doacao();
        doacao.setDataInicio(LocalDate.now());
        doacao.setEstadoDoacao(EstadoDoacao.PENDENTE); // Usando enum
        doacao.setIdRequerente(idRequerente);
        doacao.setIdDoador(idDoador);
        doacao.setIdEquipamento(idEquipamento);

        return doacaoRepository.save(doacao);
    }

	/*
	 * public Doacao finalizarDoacao(Long id) { Doacao doacao =
	 * doacaoRepository.findById(id) .orElseThrow(() -> new
	 * IllegalArgumentException("Doação não encontrada"));
	 * 
	 * doacao.setEstadoDoacao("TERMINADA"); doacao.setDataFim(LocalDate.now());
	 * 
	 * return doacaoRepository.save(doacao); }
	 */
    
    public Doacao finalizarDoacao(Long id) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doação não encontrada"));

        doacao.setEstadoDoacao(EstadoDoacao.TERMINADA); // Usando enum
        doacao.setDataFim(LocalDate.now());

        return doacaoRepository.save(doacao);
    }

	/*
	 * public Doacao cancelarDoacao(Long id) { Doacao doacao =
	 * doacaoRepository.findById(id) .orElseThrow(() -> new
	 * IllegalArgumentException("Doação não encontrada"));
	 * 
	 * doacao.setEstadoDoacao("CANCELADA"); doacao.setDataFim(LocalDate.now());
	 * 
	 * return doacaoRepository.save(doacao); }
	 */
    
    public Doacao cancelarDoacao(Long id) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doação não encontrada"));

        doacao.setEstadoDoacao(EstadoDoacao.CANCELADA); // Usando enum
        doacao.setDataFim(LocalDate.now());

        return doacaoRepository.save(doacao);
    }
    
    public List<Doacao> getAllByRequesterId(Long idRequerente) {
        return doacaoRepository.findAllByIdRequerente(idRequerente);
    }
    
    public List<Doacao> getDonationRequestsByDonor(Long doadorId) {
        return doacaoRepository.findByIdDoador(doadorId); // Este método assume que você já tem um método no repositório para buscar doações por doador
    }
    
	/*
	 * public boolean rejectDonation(Long donationId) { // Similar ao
	 * acceptDonation, mas você pode alterar o status para "REJEITADA"
	 * Optional<Doacao> doacaoOptional = doacaoRepository.findById(donationId);
	 * 
	 * if (doacaoOptional.isPresent()) { Doacao doacao = doacaoOptional.get();
	 * 
	 * // Altere o status da doação para "rejeitada"
	 * doacao.setEstadoDoacao("REJEITADA");
	 * 
	 * // Salve a doação atualizada doacaoRepository.save(doacao);
	 * 
	 * return true; } else { return false; } }
	 */
    
    public boolean rejectDonation(Long donationId) {
        Optional<Doacao> doacaoOptional = doacaoRepository.findById(donationId);

        if (doacaoOptional.isPresent()) {
            Doacao doacao = doacaoOptional.get();

            // Alterar o estado da doação para REJEITADA usando o enum
            doacao.setEstadoDoacao(EstadoDoacao.REJEITADA);

            // Salvar a doação atualizada
            doacaoRepository.save(doacao);

            return true;
        } else {
            return false;
        }
    }
    
	/*
	 * public boolean acceptDonation(Long donationId) { // Verifica se a doação
	 * existe Optional<Doacao> doacaoOptional =
	 * doacaoRepository.findById(donationId);
	 * 
	 * if (doacaoOptional.isPresent()) { Doacao doacao = doacaoOptional.get();
	 * 
	 * // Altere o status da doação para "aceita"
	 * doacao.setEstadoDoacao("ACEITADA"); // Supondo que você tenha um campo
	 * 'status' na entidade Doacao
	 * 
	 * // Salve a doação atualizada doacaoRepository.save(doacao);
	 * 
	 * return true; // Retorna true se a doação foi aceita com sucesso } else { //
	 * Retorna false caso a doação não tenha sido encontrada return false; } }
	 */
    
    public boolean acceptDonation(Long donationId) {
        Optional<Doacao> doacaoOptional = doacaoRepository.findById(donationId);

        if (doacaoOptional.isPresent()) {
            Doacao doacao = doacaoOptional.get();

            // Alterar o estado da doação para ACEITADA usando o enum
            doacao.setEstadoDoacao(EstadoDoacao.ACEITADA);

            // Salvar a doação atualizada
            doacaoRepository.save(doacao);

            return true;  // Retorna true se a doação foi aceita com sucesso
        } else {
            return false;  // Retorna false caso a doação não tenha sido encontrada
        }
}
    
}

