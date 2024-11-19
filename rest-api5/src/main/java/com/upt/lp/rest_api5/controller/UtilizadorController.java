package com.upt.lp.rest_api5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upt.lp.rest_api5.model.Utilizador;
import com.upt.lp.rest_api5.service.UtilizadorService;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    @Autowired
    private UtilizadorService utilizadorService;

    // Método para obter todos os utilizadores
    @GetMapping
    public List<Utilizador> getAllUsers() {
        return utilizadorService.getAllUsers();
    }

    // Método para obter um utilizador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> getUserById(@PathVariable Long id) {
        Optional<Utilizador> utilizador = utilizadorService.getUserById(id);
        if (utilizador.isPresent()) {
            return ResponseEntity.ok(utilizador.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	/*
	 * // Método para criar um novo utilizador
	 * 
	 * @PostMapping public ResponseEntity<Utilizador> createUser(@RequestBody
	 * Utilizador utilizador) { Utilizador newUser =
	 * utilizadorService.createUser(utilizador); return ResponseEntity.ok(newUser);
	 * }
	 */
    
    @PostMapping("/register")
    public ResponseEntity<Utilizador> registerUser(@RequestBody Utilizador utilizador) {
        try {
            Utilizador newUser = utilizadorService.registerUser(utilizador);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
	/*
	 * @PostMapping("/login") public ResponseEntity<String> loginUser(@RequestParam
	 * String email, @RequestParam String senha) { boolean isAuthenticated =
	 * utilizadorService.authenticate(email, senha);
	 * 
	 * if (isAuthenticated) { return ResponseEntity.ok("Login bem-sucedido!"); }
	 * else { return ResponseEntity.status(401).body("Credenciais inválidas."); } }
	 */
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String senha) {
        Utilizador user = utilizadorService.findByEmail(email);

        if (user != null && utilizadorService.authenticate(email, senha)) {
            return ResponseEntity.ok(user); // Retorna o objeto completo em formato JSON
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas."); // Mensagem de erro
        }
    }
    
    // Método para atualizar um utilizador
    @PutMapping("/{id}")
    public ResponseEntity<Utilizador> updateUser(@PathVariable Long id, @RequestBody Utilizador utilizador) {
        try {
            Utilizador updatedUser = utilizadorService.updateUser(id, utilizador);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/validate")
    public ResponseEntity<String> validateUserType(
        @RequestParam String email,
        @RequestParam String userType
    ) {
        boolean isValid = utilizadorService.validateUserType(email, userType);

        if (isValid) {
            return ResponseEntity.ok("Usuário válido como " + userType);
        } else {
            return ResponseEntity.badRequest().body("Usuário não é do tipo: " + userType);
        }
    }
}


