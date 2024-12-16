package com.upt.lp.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import com.upt.lp.rest_api5.model.*;


public class RestClient {

    private RestTemplate restTemplate = new RestTemplate();

    private final String rootAPIURLACE = "http://localhost:8080/api/acessorios";
    private final String rootAPIURLCOMP = "http://localhost:8080/api/computadores";
	

	public void getAcessorioById(Long id) {

		ResponseEntity<Acessorio> response = restTemplate.getForEntity(rootAPIURLACE + "/" + id.toString(),
				Acessorio.class);

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

		ResponseEntity<Computador> response = restTemplate.getForEntity(rootAPIURLCOMP + "/" + id.toString(),
				Computador.class);

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

		ResponseEntity<Acessorio[]> response = restTemplate.getForEntity(rootAPIURLACE, Acessorio[].class);

		if (response.getStatusCode().is2xxSuccessful()) {
			Acessorio[] acessorios = response.getBody();
			if (acessorios != null) {
				for (Acessorio acessorio : acessorios) {
					System.out.println();
					System.out.println();
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

		ResponseEntity<Computador[]> response = restTemplate.getForEntity(rootAPIURLCOMP, Computador[].class);

		if (response.getStatusCode().is2xxSuccessful()) {
			Computador[] computadores = response.getBody();
			if (computadores != null) {
				for (Computador computador : computadores) {
					System.out.println();
					System.out.println();
					System.out.println(computador.toString());
				}
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}

	public void createAcessorio(Acessorio acessorio) {

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
	
	public void createComputador(Computador computador) {

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
			ResponseEntity<Acessorio> response = restTemplate.getForEntity(rootAPIURLACE + "/" + id, Acessorio.class);

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
			ResponseEntity<Computador> response = restTemplate.getForEntity(rootAPIURLCOMP + "/" + id,
					Computador.class);

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

	public void updateAcessorio(Long id, Acessorio acessorio) {
		
		try {

			ResponseEntity<Acessorio> response = restTemplate.getForEntity(rootAPIURLACE + "/" + id, Acessorio.class);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				restTemplate.put(rootAPIURLACE + "/" + id, acessorio);
				System.out.println("Accessory with ID " + id + " updated successfully!");
			} else {
				System.out.println("Accessory with ID " + id + " not found.");
			}
		} catch (HttpClientErrorException.NotFound e) {
			System.out.println("Accessory with ID " + id + " not found (404 Not Found).");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public void updateComputador(Long id, Computador computador) {
	   
	    try {
	        
	        ResponseEntity<Computador> response = restTemplate.getForEntity(rootAPIURLCOMP + "/" + id, Computador.class);

	        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	            restTemplate.put(rootAPIURLCOMP + "/" + id, computador);
	            System.out.println("Computer with ID " + id + " updated successfully!");

	        } else {
	            System.out.println("Computer with ID " + id + " not found.");
	        }
	    } catch (HttpClientErrorException.NotFound e) {
	        System.out.println("Computer with ID " + id + " not found (404 Not Found).");
	    } catch (Exception e) {
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	}
	
}