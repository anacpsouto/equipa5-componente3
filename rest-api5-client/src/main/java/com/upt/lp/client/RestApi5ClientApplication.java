package com.upt.lp.client;

import java.awt.print.Book;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

//import com.upt.donations.model.UserType;
import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.model.Computador;

@SpringBootApplication
public class RestApi5ClientApplication {

	private RestTemplate restTemplate = new RestTemplate();

	private String rootAPIURLACE = "http://localhost:8080/api/acessorios";

	private String rootAPIURLCOMP = "http://localhost:8080/api/computadores";
	
	//private String rootAPIURLUSER = "http://localhost:8080/api/users";

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

	public void createAcessorio(Scanner scanner) {

		Acessorio acessorio = new Acessorio();

		System.out.print("\nEnter the accessory type: (EX: HEADSET, MOUSE, KEYBOARD, MOUSEPAD) ");
		String accessoryType = scanner.nextLine();
		acessorio.setTipo(accessoryType);

		System.out.print("\nEnter more information about the accessory: ");
		String moreInformation = scanner.nextLine();
		acessorio.setInformacao(moreInformation);

		System.out.println("\nEnter a name to identify the accessory:");
		String accessoryName = scanner.nextLine();
		acessorio.setNome(accessoryName);

		System.out.print("\nEnter the year: ");
		String accessoryYear = scanner.nextLine();
		acessorio.setAno(accessoryYear);

		System.out.print("\nEnter accessory brand: ");
		String accessoryBrand = scanner.nextLine();
		acessorio.setBrand(accessoryBrand);

		System.out.print("\nEnter the accessory conservation status:");
		String accessoryConservation = scanner.nextLine();
		acessorio.setEstadoConservacao(accessoryConservation);

		/*
		 * book.setAuthor("Thiago Test 071124"); book.setTitle("Test Book 071124");
		 * book.setIsbn("98731071124");
		 */

		ResponseEntity<Acessorio> response = restTemplate.postForEntity(rootAPIURLACE, acessorio, Acessorio.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			Acessorio body = response.getBody();
			if (body != null) {
				System.out.println(body.toString());
				System.out.println("Equipment added successfully!");
			} else {
				System.out.println("No body");
			}
		} else {
			System.out.println("Nothing found");
		}
	}

	public void createComputador(Scanner scanner) {

		Computador computador = new Computador();

		System.out.print("\nEnter the type of computer: (DESKTOP or LAPTOP): ");
		String computerType = scanner.nextLine();
		computador.setType(computerType);

		System.out.print("\nEnter RAM capacity: (EX: 4GB, 8GB, 16GB, 32GB or more): ");
		String ramCapacity = scanner.nextLine();
		computador.setRam(ramCapacity);

		System.out.print("\nEnter the drive type: (EX: HDD (Hard Disk Drive) or SSD (Solid State Drive): ");
		String driveType = scanner.nextLine();
		computador.setDisco(driveType);

		System.out.print("\nEnter the processor (EX: Intel, AMD, Apple Silicon):  ");
		String processorType = scanner.nextLine();
		computador.setProcessador(processorType);

		System.out.print("\nEnter the name: ");
		String computerName = scanner.nextLine();
		computador.setNome(computerName);

		System.out.print("\nEnter the year: ");
		String computerYear = scanner.nextLine();
		computador.setAno(computerYear);

		System.out.print("\nEnter computer brand (EX: Dell, HP, Lenovo, Apple, Acer, ASUS): ");
		String computerbrand = scanner.nextLine();
		computador.setBrand(computerbrand);

		System.out.print("\nEnter the equipment conservation status:");
		String computerConservation = scanner.nextLine();
		computador.setEstadoConservacao(computerConservation);

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

	public void updateAcessorio() {

		Acessorio acessorio = new Acessorio();

		acessorio.setTipo("Calculadora");
		acessorio.setInformacao("Calculadora gráfica");

		/*
		 * book.setId((long) 8); book.setAuthor("Thiago Test");
		 * book.setTitle("Test Book"); book.setIsbn("98731");
		 */

		// ResponseEntity<Acessorio> response =
		// restTemplate.postForEntity(rootAPIURLACE, acessorio, Acessorio.class);

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
			/*
			 * System.out.println("\n===== MENU =====");
			 * System.out.println("1. Criar Acessório"); --------- OK
			 * System.out.println("2. Listar Acessórios");
			 * System.out.println("3. Deletar Acessório");
			 * System.out.println("4. Criar Computador"); --------- OK
			 * System.out.println("5. Listar Computadores");
			 * System.out.println("6. Deletar Computador"); System.out.println("7. Sair");
			 * System.out.print("Escolha uma opção: ");
			 */

			System.out.println("\n===== DONOR MENU =====");
			System.out.println("1. Add Equipment");
			System.out.println("2. Search Equipment");
			// System.out.println("3. Edit Equipment");
			 System.out.println("3. Delete Equipment");

			int option = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (option) {
			case 1:
				System.out.println("\nWhat type of equipment will be add? Choose one of the options below.\n"
									+ "1: Computer (or notebook)\n" 
									+ "2: Accessories");
				int choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					myApp.createComputador(scanner);
					break;
				} else {
					myApp.createAcessorio(scanner);
					break;
				}
			case 2:
				System.out.println("\nWhat type of equipment do you want to research? Choose one of the options below.\n"
									+ "1: Computer (or notebook)\n" 
									+ "2: Accessories");
				choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					System.out.println("\nDo you want to search for all computers or for a specific one? Choose one of the options below.\n"
										+ "1: All computers. \n"
										+ "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();
					
					if(choiceResearchType == 1) {
						myApp.getAllComputadores(); 
						break;
					} else {
						System.out.println("\nEnter the ID of the computer you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						myApp.getComputadorById(idSearch);
						break;
					}
				} else {
					System.out.println("\nDo you want to search for all accessories or for a specific one? Choose one of the options below.\n"
							+ "1: All accessories. \n"
							+ "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();
		
					if(choiceResearchType == 1) {
						myApp.getAllAcessorios();
						break;
					} else {
						System.out.print("\nEnter the ID of the accessory you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						myApp.getAcessorioById(idSearch);
						break;
					}

				}
			case 3:
				System.out.println("\nWhat type of equipment do you want to delete? Choose one of the options below.\n"
						+ "1: Computer (or notebook)\n" 
						+ "2: Accessories");
				choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					System.out.print("\nEnter the ID of the computer you want to delete: ");
					Long idToDelete = scanner.nextLong();
					scanner.nextLine();
					myApp.deleteComputador(idToDelete);
					break;
				}
				else {
					System.out.print("\nEnter the ID of the accessory you want to delete: ");
					Long idToDelete = scanner.nextLong();
					scanner.nextLine();
					myApp.deleteAcessorio(idToDelete);
					break;
				}

			/*
			 * case 3: System.out.print("Digite o ID do acessório a ser deletado: "); Long
			 * acessorioId = scanner.nextLong(); myApp.deleteAcessorio(acessorioId); break;
			 case 6:
			 * System.out.print("Digite o ID do computador a ser deletado: "); Long
			 * computadorId = scanner.nextLong(); myApp.deleteComputador(computadorId);
			 * break; case 7: System.out.println("Encerrando o programa...");
			 * scanner.close(); System.exit(0);
			 */
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
}
