package com.upt.lp.client;

import java.awt.print.Book;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.model.Computador;



@SpringBootApplication
public class RestApi5ClientApplication {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private String rootAPIURLACE = "http://localhost:8080/api/acessorios";
	
	private String rootAPIURLCOMP = "http://localhost:8080/api/computadores";
	
	public void getAcessorioById(Long id) {
		
		ResponseEntity<Acessorio> response = restTemplate.getForEntity(rootAPIURLACE + "/" + id.toString(), Acessorio.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			Acessorio body = response.getBody();
			if (body != null) {		
					System.out.println(body.toString());
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void getComputadorById(Long id) {
		
		ResponseEntity<Computador> response = restTemplate.getForEntity(rootAPIURLCOMP + "/" + id.toString(), Computador.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			Computador body = response.getBody();
			if (body != null) {		
					System.out.println(body.toString());
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void getAllAcessorios() {
		
		ResponseEntity<Acessorio[]> response =
				  restTemplate.getForEntity(
				  rootAPIURLACE,
				  Acessorio[].class);
		
	
		if (response.getStatusCode().is2xxSuccessful()) {
			Acessorio[] acessorios = response.getBody();
			if (acessorios != null) {		
				for (Acessorio acessorio : acessorios) {
					System.out.println(acessorio.toString());
				}					
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void getAllComputadores() {
		
		ResponseEntity<Computador[]> response =
				  restTemplate.getForEntity(
				  rootAPIURLCOMP,
				  Computador[].class);
		
	
		if (response.getStatusCode().is2xxSuccessful()) {
			Computador[] computadores = response.getBody();
			if (computadores != null) {		
				for (Computador computador : computadores) {
					System.out.println(computador.toString());
				}					
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void createAcessorio() {
		
		Acessorio acessorio = new Acessorio();
		
		acessorio.setTipo("Rato");
		acessorio.setInformacao("Rato wireless");
		acessorio.setNome("rato do diogo");
		acessorio.setAno("2005");
		acessorio.setModelo("logitech");
		acessorio.setEstadoConservacao("bom estado");
		
		/*
		 * book.setAuthor("Thiago Test 071124"); book.setTitle("Test Book 071124");
		 * book.setIsbn("98731071124");
		 */
		
		ResponseEntity<Acessorio> response = restTemplate.postForEntity(rootAPIURLACE, acessorio, Acessorio.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			Acessorio body = response.getBody();
			if (body != null) {		
					System.out.println(body.toString());
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void createComputador() {
		
		Computador computador = new Computador();
		
		computador.setTipo("Portátil");
		computador.setRam("8GB");
		computador.setDisco("quadra");
		computador.setProcessador("yes");
		computador.setNome("computador da ana");
		computador.setAno("2002");
		computador.setModelo("macbook");
		computador.setEstadoConservacao("bom estado");
		
		ResponseEntity<Computador> response = restTemplate.postForEntity(rootAPIURLCOMP, computador, Computador.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			Computador body = response.getBody();
			if (body != null) {		
					System.out.println(body.toString());
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}
	
	public void deleteAcessorio(Long id) {
	    try {
	        // Primeiro, pesquisar o livro pelo ID para verificar se ele existe
	        ResponseEntity<Book> response = restTemplate.getForEntity(rootAPIURLACE + "/" + id, Book.class);
	        
	        if (response.getStatusCode().is2xxSuccessful()) {
	            // O livro existe, agora podemos fazer o DELETE
	            restTemplate.delete(rootAPIURLACE + "/" + id);
	            System.out.println("Acessory with ID " + id + " deleted successfully.");
	        } else {
	            // Caso o livro não exista, imprimir mensagem
	            System.out.println("Acessory with ID " + id + " not found.");
	        }
	    } catch (HttpClientErrorException.NotFound e) {
	        // Captura o erro 404 e imprime mensagem de livro não encontrado
	        System.out.println("Acessory with ID " + id + " not found (404 Not Found).");
	    } catch (Exception e) {
	        // Captura outras exceções e imprime mensagem genérica
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	}
	
	public void deleteComputador(Long id) {
	    try {
	        // Primeiro, pesquisar o livro pelo ID para verificar se ele existe
	        ResponseEntity<Computador> response = restTemplate.getForEntity(rootAPIURLCOMP + "/" + id, Computador.class);
	        
	        if (response.getStatusCode().is2xxSuccessful()) {
	            // O livro existe, agora podemos fazer o DELETE
	            restTemplate.delete(rootAPIURLCOMP + "/" + id);
	            System.out.println("Computer with ID " + id + " deleted successfully.");
	        } else {
	            // Caso o livro não exista, imprimir mensagem
	            System.out.println("Computer with ID " + id + " not found.");
	        }
	    } catch (HttpClientErrorException.NotFound e) {
	        // Captura o erro 404 e imprime mensagem de livro não encontrado
	        System.out.println("Computer with ID " + id + " not found (404 Not Found).");
	    } catch (Exception e) {
	        // Captura outras exceções e imprime mensagem genérica
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	}
	
	public void updateAcessorio() {
		
		Acessorio acessorio = new Acessorio();
		
		acessorio.setTipo("Calculadora");
		acessorio.setInformacao("Calculadora gráfica");
		
		/*
		 * book.setId((long) 8); book.setAuthor("Thiago Test");
		 * book.setTitle("Test Book"); book.setIsbn("98731");
		 */
		
		//ResponseEntity<Acessorio> response = restTemplate.postForEntity(rootAPIURLACE, acessorio, Acessorio.class);
		
		/*
		 * if (response.getStatusCode().is2xxSuccessful()) { Acessorio body =
		 * response.getBody(); if (body != null) { System.out.println(body.toString());
		 * } else { System.out.println("No body"); } } else {
		 * System.out.println("Nothing found"); }
		 */
		
		restTemplate.put(rootAPIURLACE + "/" + acessorio.getId(), acessorio);
	    System.out.println("Acessorio updated successfully");
	}
	
	// metodo update computador

	public static void main(String[] args) {
		
		
		
		
		/*
		 * //myApp.updateAcessorio(); //System.out.println("Sucessfull");
		 * myApp.createAcessorio(); System.out.println("Sucessfull");
		 * myApp.getAllAcessorios(); System.out.println("Sucessfull");
		 * 
		 * myApp.getAcessorioById((long) 3); System.out.println("Sucessfull");
		 * //myApp.deleteAcessorio((long) 20); //System.out.println("Sucessfull");
		 * //myApp.getAllAcessorios(); //System.out.println("Sucessfull");
		 */
		
		RestApi5ClientApplication myApp = new RestApi5ClientApplication();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Criar Acessório");
            System.out.println("2. Listar Acessórios");
            System.out.println("3. Deletar Acessório");
            System.out.println("4. Criar Computador");
            System.out.println("5. Listar Computadores");
            System.out.println("6. Deletar Computador");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    myApp.createAcessorio();
                    break;
                case 2:
                    myApp.getAllAcessorios();
                    break;
                case 3:
                    System.out.print("Digite o ID do acessório a ser deletado: ");
                    Long acessorioId = scanner.nextLong();
                    myApp.deleteAcessorio(acessorioId);
                    break;
                case 4:
                    myApp.createComputador();
                    break;
                case 5:
                    myApp.getAllComputadores();
                    break;
                case 6:
                    System.out.print("Digite o ID do computador a ser deletado: ");
                    Long computadorId = scanner.nextLong();
                    myApp.deleteComputador(computadorId);
                    break;
                case 7:
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
