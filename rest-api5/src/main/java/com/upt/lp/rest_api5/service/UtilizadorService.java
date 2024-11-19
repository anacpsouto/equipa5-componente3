package com.upt.lp.rest_api5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.lp.rest_api5.model.Utilizador;
import com.upt.lp.rest_api5.repository.UtilizadorRepository;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    // Método para obter todos os utilizadores
    public List<Utilizador> getAllUsers() {
        return utilizadorRepository.findAll();
    }

    // Método para encontrar um utilizador por ID
    public Optional<Utilizador> getUserById(Long id) {
        return utilizadorRepository.findById(id);
    }

	/*
	 * // Método para criar um novo utilizador public Utilizador
	 * createUser(Utilizador utilizador) { return
	 * utilizadorRepository.save(utilizador);
	 * }
	 */
    
 // Registrar um novo utilizador
    public Utilizador registerUser(Utilizador utilizador) {
        // Verificar se o email já está registrado
        if (utilizadorRepository.findByEmail(utilizador.getEmail()) != null) {
            throw new RuntimeException("Email já registrado.");
        }

        // Criar o novo utilizador
        return utilizadorRepository.save(utilizador);
    }

    
    public Utilizador updateUser(Long id, Utilizador utilizador) {
        Optional<Utilizador> existingUtilizador = utilizadorRepository.findById(id);
        if (existingUtilizador.isPresent()) {
            Utilizador updatedUtilizador = existingUtilizador.get();
            updatedUtilizador.setNome(utilizador.getNome());
            updatedUtilizador.setEmail(utilizador.getEmail());
            updatedUtilizador.setSenha(utilizador.getSenha());
            updatedUtilizador.setUserType(utilizador.getUserType());
            return utilizadorRepository.save(updatedUtilizador);
        } else {
            throw new RuntimeException("Utilizador com ID " + id + " não encontrado.");
        }
    }
    
    public boolean validateUserType(String email, String userType) {
        Utilizador user = utilizadorRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        return user.getUserType().equalsIgnoreCase(userType);
    }
    
    // Autenticar credenciais de login
    public boolean authenticate(String email, String senha) {
        Utilizador user = utilizadorRepository.findByEmail(email);

        if (user != null && user.getSenha().equals(senha)) {
            return true; // Login bem-sucedido
        }

        return false; // Credenciais inválidas
    }
    
    public Utilizador findByEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }
}


