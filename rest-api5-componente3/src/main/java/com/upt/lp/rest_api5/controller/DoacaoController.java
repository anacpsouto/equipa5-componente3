package com.upt.lp.rest_api5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upt.lp.rest_api5.model.Doacao;
import com.upt.lp.rest_api5.service.DoacaoService;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Doacao> registrarPedidoDoacao(
            @RequestParam Long idRequerente,
            @RequestParam Long idDoador,
            @RequestParam Long idEquipamento) {

        Doacao doacao = doacaoService.registrarPedidoDoacao(idRequerente, idDoador, idEquipamento);
        return ResponseEntity.ok(doacao);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Doacao> finalizarDoacao(@PathVariable Long id) {
        Doacao doacao = doacaoService.finalizarDoacao(id);
        return ResponseEntity.ok(doacao);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Doacao> cancelarDoacao(@PathVariable Long id) {
        Doacao doacao = doacaoService.cancelarDoacao(id);
        return ResponseEntity.ok(doacao);
    }

    @GetMapping("/donation-requests-by-donor")
    public ResponseEntity<List<Doacao>> getDonationRequestsByDonor(@RequestParam Long doadorId) {
        List<Doacao> donationRequests = doacaoService.getDonationRequestsByDonor(doadorId);
        return ResponseEntity.ok(donationRequests);
    }
    
	/*
	 * @PutMapping("/accept-donation/{donationId}") public ResponseEntity<String>
	 * acceptDonation(@PathVariable Long donationId) { // Lógica para aceitar a
	 * doação // Exemplo: Verificar se a doação existe, e então marcar como aceita
	 * boolean success = doacaoService.acceptDonation(donationId);
	 * 
	 * if (success) { return ResponseEntity.ok("Donation accepted successfully."); }
	 * else { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donation not found."); } }
	 */
    
    @GetMapping("/all-by-requester-id")
    public ResponseEntity<List<Doacao>> getAllByRequesterId(@RequestParam Long idRequerente) {
        try {
            List<Doacao> donations = doacaoService.getAllByRequesterId(idRequerente);
            return ResponseEntity.ok(donations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/accept-donation/{donationId}")
    public ResponseEntity<String> acceptDonation(@PathVariable Long donationId) {
        // Chama o serviço para aceitar a doação
        boolean success = doacaoService.acceptDonation(donationId);

        if (success) {
            return ResponseEntity.ok("Donation accepted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donation not found.");
        }
    }
    
    @PutMapping("/reject-donation/{donationId}")
    public ResponseEntity<String> rejectDonation(@PathVariable Long donationId) {
        // Chama o serviço para rejeitar a doação
        boolean success = doacaoService.rejectDonation(donationId);

        if (success) {
            return ResponseEntity.ok("Donation rejected successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donation not found.");
        }
    }

}
