package com.upt.lp.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

//import com.upt.donations.model.UserType;
import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.model.ConservationStatus;
import com.upt.lp.rest_api5.model.Doacao;
import com.upt.lp.rest_api5.model.UserType;
import com.upt.lp.rest_api5.model.Utilizador;

@SpringBootApplication
public class RestApi5ClientApplication {

	private RestTemplate restTemplate = new RestTemplate();

	private String rootAPIURLACE = "http://localhost:8080/api/acessorios";

	private String rootAPIURLCOMP = "http://localhost:8080/api/computadores";

	private String rootAPIURLUSER = "http://localhost:8080/api/utilizadores";

	private String rootAPIURLDOAC = "http://localhost:8080/api/doacoes";

	public void registerUser(Utilizador utilizador) {


	ResponseEntity<Utilizador> response = restTemplate.postForEntity(rootAPIURLUSER + "/register", utilizador,
						Utilizador.class);

	if(response.getStatusCode().is2xxSuccessful())
	{
		System.out.println("User registered successfully!");
	}else
	{
		System.out.println("Failed to register user.");
	}
	}

	public void login(String email, String password, Scanner scanner) {
		/*System.out.print("Enter your email: ");
		String email = scanner.nextLine();

		System.out.print("Enter your password: ");
		String password = scanner.nextLine();*/

		String loginUrl = rootAPIURLUSER + "/login?email=" + email + "&senha=" + password;

		try {
			ResponseEntity<Utilizador> response = restTemplate.postForEntity(loginUrl, null, Utilizador.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				Utilizador loggedInUser = response.getBody();
				System.out.println("Login successful! Welcome, " + loggedInUser.getNome());

				// Obtendo o ID do usuário logado
				Long loggedInUserId = loggedInUser.getId();

				// Menu com base no tipo de usuário
				if (loggedInUser.getUserType() == UserType.DONOR) {
					donorMenu(scanner, loggedInUserId);
				} else if (loggedInUser.getUserType() == UserType.RECIPIENT) {
					recipientMenu(scanner, loggedInUserId);
					
				} else if (loggedInUser.getUserType() == UserType.MANAGER) {
					//recipientMenu(scanner, loggedInUserId);
					System.out.println("VOCE SERA DIRECIONADO PARA O MENU DO GERENTE ---- TESTE");
				} else {
					System.out.println("Invalid user type.");
				}

			} else {
				System.out.println("Invalid login credentials.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void searchManager(String email, String password) {
		String loginUrl = rootAPIURLUSER + "/login?email=" + email + "&senha=" + password;
		
		try {
			ResponseEntity<Utilizador> response = restTemplate.postForEntity(loginUrl, null, Utilizador.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				Utilizador loggedInUser = response.getBody();
				System.out.println("ADM CADASTRADO!");
				System.out.println("Login successful! Welcome, " + loggedInUser.getNome());
			} 
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			System.out.println("ADM NÃO FOI ENCONTRADO NO BANCO DE DADOS, MAS FOI CADASTRADO");
			Utilizador utilizador = new Utilizador(null, "Carlos", "carlos@gmail.com", "Eucarlos@2001", UserType.MANAGER);
			RestApi5ClientApplication restApi = new RestApi5ClientApplication();
			restApi.registerUser(utilizador);
		}
	}
		
		

	public void updateUser(Scanner scanner) {
		System.out.print("\nEnter the ID of the user to update: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		try {
			ResponseEntity<Utilizador> response = restTemplate.getForEntity(rootAPIURLUSER + "/" + id,
					Utilizador.class);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				Utilizador utilizador = response.getBody();

				System.out.println("Current details of the user: " + utilizador.toString());

				System.out.print("\nEnter new username (leave empty to keep current): ");
				String newUsername = scanner.nextLine();
				if (!newUsername.isEmpty())
					utilizador.setNome(newUsername);

				System.out.print("\nEnter new email (leave empty to keep current): ");
				String newEmail = scanner.nextLine();
				if (!newEmail.isEmpty())
					utilizador.setEmail(newEmail);

				System.out.print("\nEnter new password (leave empty to keep current): ");
				String newPassword = scanner.nextLine();
				if (!newPassword.isEmpty())
					utilizador.setSenha(newPassword);

				restTemplate.put(rootAPIURLUSER + "/" + id, utilizador);
				System.out.println("User with ID " + id + " updated successfully!");

			} else {
				System.out.println("User with ID " + id + " not found.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

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

	public void createAcessorio(Scanner scanner, Long loggedInUserId) {

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
		ConservationStatus accessoryConservation = ConservationStatus.valueOf(scanner.nextLine().toUpperCase());
		acessorio.setEstadoConservacao(accessoryConservation);

		// Atribuindo o userID (o ID do usuário logado)
		acessorio.setUserID(loggedInUserId); // Atribuindo o ID do usuário logado ao acessório

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

	public void createComputador(Scanner scanner, Long loggedInUserId) {

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
		ConservationStatus computerConservation = ConservationStatus.valueOf(scanner.nextLine().toUpperCase());
		computador.setEstadoConservacao(computerConservation);

		// Atribuindo o userID (o ID do usuário logado)
		computador.setUserID(loggedInUserId); // Esse é o ponto chave. Preencha com o ID do usuário

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

	public void updateAcessorio(Scanner scanner) {
		System.out.print("\nEnter the ID of the accessory to update: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		try {

			ResponseEntity<Acessorio> response = restTemplate.getForEntity(rootAPIURLACE + "/" + id, Acessorio.class);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				Acessorio acessorio = response.getBody();

				System.out.println("Current details of the accessory: " + acessorio.toString());

				System.out.print(
						"\nEnter the new accessory type (EX: HEADSET, MOUSE, KEYBOARD, MOUSEPAD) (leave empty to keep current): ");
				String newType = scanner.nextLine();
				if (!newType.isEmpty())
					acessorio.setTipo(newType);

				System.out.print("\nEnter new information about the accessory (leave empty to keep current): ");
				String newInfo = scanner.nextLine();
				if (!newInfo.isEmpty())
					acessorio.setInformacao(newInfo);

				System.out.print("\nEnter a new name for the accessory (leave empty to keep current): ");
				String newName = scanner.nextLine();
				if (!newName.isEmpty())
					acessorio.setNome(newName);

				System.out.print("\nEnter a new year for the accessory (leave empty to keep current): ");
				String newYear = scanner.nextLine();
				if (!newYear.isEmpty())
					acessorio.setAno(newYear);

				System.out.print("\nEnter a new brand for the accessory (leave empty to keep current): ");
				String newBrand = scanner.nextLine();
				if (!newBrand.isEmpty())
					acessorio.setBrand(newBrand);

				System.out
						.print("\nEnter the new conservation status of the accessory (leave empty to keep current): ");

				String inputAcessoryConservation = scanner.nextLine().trim().toUpperCase();

				if (!inputAcessoryConservation.isEmpty()) {
					try {
						ConservationStatus newConservation = ConservationStatus.valueOf(inputAcessoryConservation);
						acessorio.setEstadoConservacao(newConservation);
						restTemplate.put(rootAPIURLACE + "/" + id, acessorio);
						System.out.println("Accessory with ID " + id + " updated successfully!");
						
					} catch (IllegalArgumentException e) {
						System.out.println("Estado de conservação inválido. Tente novamente.");
					}
				}

				/*
				 * ConservationStatus newConservation =
				 * ConservationStatus.valueOf(scanner.nextLine().toUpperCase()); if
				 * (!newConservation.isEmpty()) acessorio.setEstadoConservacao(newConservation);
				 */

				

			} else {
				System.out.println("Accessory with ID " + id + " not found.");
			}
		} catch (HttpClientErrorException.NotFound e) {
			System.out.println("Accessory with ID " + id + " not found (404 Not Found).");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public void updateComputador(Scanner scanner) {
		System.out.print("\nEnter the ID of the computer to update: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		try {

			ResponseEntity<Computador> response = restTemplate.getForEntity(rootAPIURLCOMP + "/" + id,
					Computador.class);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				Computador computador = response.getBody();

				System.out.println("Current details of the computer: " + computador.toString());

				System.out
						.print("\nEnter the new type of computer (DESKTOP or LAPTOP) (leave empty to keep current): ");
				String newType = scanner.nextLine();
				if (!newType.isEmpty())
					computador.setType(newType);

				System.out.print(
						"\nEnter new RAM capacity (EX: 4GB, 8GB, 16GB, 32GB or more) (leave empty to keep current): ");
				String newRam = scanner.nextLine();
				if (!newRam.isEmpty())
					computador.setRam(newRam);

				System.out.print("\nEnter the new drive type (HDD or SSD) (leave empty to keep current): ");
				String newDrive = scanner.nextLine();
				if (!newDrive.isEmpty())
					computador.setDisco(newDrive);

				System.out.print(
						"\nEnter the new processor type (EX: Intel, AMD, Apple Silicon) (leave empty to keep current): ");
				String newProcessor = scanner.nextLine();
				if (!newProcessor.isEmpty())
					computador.setProcessador(newProcessor);

				System.out.print("\nEnter a new name for the computer (leave empty to keep current): ");
				String newName = scanner.nextLine();
				if (!newName.isEmpty())
					computador.setNome(newName);

				System.out.print("\nEnter a new year for the computer (leave empty to keep current): ");
				String newYear = scanner.nextLine();
				if (!newYear.isEmpty())
					computador.setAno(newYear);

				System.out.print(
						"\nEnter a new brand for the computer (EX: Dell, HP, Lenovo, Apple, Acer, ASUS) (leave empty to keep current): ");
				String newBrand = scanner.nextLine();
				if (!newBrand.isEmpty())
					computador.setBrand(newBrand);

				System.out.print("\nEnter the new conservation status of the computer (leave empty to keep current): ");
				String inputConservation = scanner.next().toUpperCase();

				if (!inputConservation.isEmpty()) {
					try {
						ConservationStatus newConservation = ConservationStatus.valueOf(inputConservation);
						computador.setEstadoConservacao(newConservation);
					} catch (IllegalArgumentException e) {
						System.out.println("Estado de conservação inválido. Tente novamente.");
					}
				}

				// ConservationStatus newConservation =
				// ConservationStatus.valueOf(scanner.nextLine().toUpperCase());
				// if (!newConservation.isEmpty())
				// computador.setEstadoConservacao(newConservation);

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

	/*
	 * private void donorMenu(Scanner scanner, Long loggedInUserId) { while (true) {
	 * 
	 * System.out.println("\n===== DONOR MENU =====");
	 * System.out.println("1. Add Equipment");
	 * System.out.println("2. Search Equipment");
	 * System.out.println("3. Edit Equipment");
	 * System.out.println("4. Delete Equipment");
	 * 
	 * int option = scanner.nextInt(); scanner.nextLine(); // Consumir a nova linha
	 * 
	 * switch (option) { case 1: System.out.
	 * println("\nWhat type of equipment will be add? Choose one of the options below.\n"
	 * + "1: Computer (or notebook)\n" + "2: Accessories"); int choiceEquipmentType
	 * = scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (choiceEquipmentType == 1) { createComputador(scanner, loggedInUserId);
	 * break; } else { createAcessorio(scanner, loggedInUserId); break; } case 2:
	 * System.out
	 * .println("\nWhat type of equipment do you want to research? Choose one of the options below.\n"
	 * + "1: Computer (or notebook)\n" + "2: Accessories"); choiceEquipmentType =
	 * scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (choiceEquipmentType == 1) { System.out.println(
	 * "\nDo you want to search for all computers or for a specific one? Choose one of the options below.\n"
	 * + "1: All computers. \n" + "2: A specific. "); int choiceResearchType =
	 * scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (choiceResearchType == 1) { getAllComputadores(); break; } else {
	 * System.out.println("\nEnter the ID of the computer you want to search: ");
	 * Long idSearch = scanner.nextLong(); scanner.nextLine();
	 * getComputadorById(idSearch); break; } } else { System.out.println(
	 * "\nDo you want to search for all accessories or for a specific one? Choose one of the options below.\n"
	 * + "1: All accessories. \n" + "2: A specific. "); int choiceResearchType =
	 * scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (choiceResearchType == 1) { getAllAcessorios(); break; } else {
	 * System.out.print("\nEnter the ID of the accessory you want to search: ");
	 * Long idSearch = scanner.nextLong(); scanner.nextLine();
	 * getAcessorioById(idSearch); break; }
	 * 
	 * }
	 * 
	 * case 3: System.out.
	 * println("\nWhat type of equipment do you want to update? Choose one of the options below.\n"
	 * + "1: Computer (or notebook)\n" + "2: Accessories"); choiceEquipmentType =
	 * scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (choiceEquipmentType == 1) { updateComputador(scanner); } else {
	 * updateAcessorio(scanner); } break;
	 * 
	 * 
	 * case 4: System.out.
	 * println("\nWhat type of equipment do you want to delete? Choose one of the options below.\n"
	 * + "1: Computer (or notebook)\n" + "2: Accessories"); choiceEquipmentType =
	 * scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (choiceEquipmentType == 1) {
	 * System.out.print("\nEnter the ID of the computer you want to delete: "); Long
	 * idToDelete = scanner.nextLong(); scanner.nextLine();
	 * deleteComputador(idToDelete); break; } else {
	 * System.out.print("\nEnter the ID of the accessory you want to delete: ");
	 * Long idToDelete = scanner.nextLong(); scanner.nextLine();
	 * deleteAcessorio(idToDelete); break; }
	 * 
	 * default: System.out.println("Opção inválida. Tente novamente."); } }
	 * 
	 * }
	 */

	private void donorMenu(Scanner scanner, Long loggedInUserId) {
		// while (true) {
		int option;
		do {
			System.out.println("\n===== DONOR MENU =====");
			System.out.println("1. Add Equipment");
			System.out.println("2. Search Equipment");
			System.out.println("3. Edit Equipment");
			System.out.println("4. Delete Equipment");
			System.out.println("5. View Donation Requests");
			System.out.println("6. Exit");

			option = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			System.out.println(option);
			switch (option) {
			case 1:
				System.out.println("\nWhat type of equipment will be added? Choose one of the options below.\n"
						+ "1: Computer (or notebook)\n" + "2: Accessories");
				int choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					createComputador(scanner, loggedInUserId);
					break;
				} else {
					createAcessorio(scanner, loggedInUserId);
					break;
				}
			case 2:
				System.out
						.println("\nWhat type of equipment do you want to research? Choose one of the options below.\n"
								+ "1: Computer (or notebook)\n" + "2: Accessories");
				choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					System.out.println(
							"\nDo you want to search for all computers or for a specific one? Choose one of the options below.\n"
									+ "1: All computers. \n" + "2: A specific one.");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
						getAllComputadores();
					} else {
						System.out.println("\nEnter the ID of the computer you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						getComputadorById(idSearch);
					}
					break;
				} else {
					System.out.println(
							"\nDo you want to search for all accessories or for a specific one? Choose one of the options below.\n"
									+ "1: All accessories. \n" + "2: A specific one.");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
						getAllAcessorios();
					} else {
						System.out.print("\nEnter the ID of the accessory you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						getAcessorioById(idSearch);
					}
					break;
				}

			case 3:
				System.out.println("\nWhat type of equipment do you want to update? Choose one of the options below.\n"
						+ "1: Computer (or notebook)\n" + "2: Accessories");
				choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					updateComputador(scanner);
				} else {
					updateAcessorio(scanner);
				}
				break;

			case 4:
				System.out.println("\nWhat type of equipment do you want to delete? Choose one of the options below.\n"
						+ "1: Computer (or notebook)\n" + "2: Accessories");
				choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					System.out.print("\nEnter the ID of the computer you want to delete: ");
					Long idToDelete = scanner.nextLong();
					scanner.nextLine();
					deleteComputador(idToDelete);
					break;
				} else {
					System.out.print("\nEnter the ID of the accessory you want to delete: ");
					Long idToDelete = scanner.nextLong();
					scanner.nextLine();
					deleteAcessorio(idToDelete);
					break;
				}

			case 5:
				// Verificar se há pedidos de doação recebidos
				checkDonationRequests(scanner, loggedInUserId);
				break;

			case 6:
				System.out.println("Exiting Donor Menu...");
				break;

			default:
				System.out.println("Invalid option. Please try again.");
			}
		} while (option != 6);
	}

	private List<Doacao> getDonationRequestsByDonor(Long loggedInUserId) {
		// URL para consultar pedidos de doação feitos ao doador
		String url = rootAPIURLDOAC + "/donation-requests-by-donor?doadorId=" + loggedInUserId;

		try {
			// Enviar a requisição GET para a API e receber uma lista de Doacao
			ResponseEntity<List<Doacao>> response = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Doacao>>() {
					});

			// Verificar se a resposta foi bem-sucedida
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				return response.getBody(); // Retorna a lista de pedidos de doação
			} else {
				System.out.println("No donation requests found for this donor.");
				return new ArrayList<>(); // Retorna uma lista vazia se não houver pedidos
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
		}
	}

	/*
	 * private void checkDonationRequests(Scanner scanner, Long loggedInUserId) { //
	 * Chama o método getDonationRequestsByDonor para obter os pedidos de doação da
	 * API List<Doacao> donationRequests =
	 * getDonationRequestsByDonor(loggedInUserId);
	 * 
	 * if (donationRequests != null && !donationRequests.isEmpty()) {
	 * System.out.println("\nYou have received the following donation requests:");
	 * 
	 * for (Doacao donation : donationRequests) { System.out.println("Donation ID: "
	 * + donation.getId() + ", Requestor ID: " + donation.getIdRequerente());
	 * System.out.println("1: Accept\n2: Reject"); int choice = scanner.nextInt();
	 * scanner.nextLine(); // Consumir nova linha
	 * 
	 * if (choice == 1) { // Aceitar a doação acceptDonation(donation.getId()); }
	 * else { // Recusar a doação rejectDonation(donation.getId()); } } } else {
	 * System.out.println("\nNo donation requests at the moment."); } }
	 */

	private void checkDonationRequests(Scanner scanner, Long loggedInUserId) {
		// Chama o método getDonationRequestsByDonor para obter os pedidos de doação da
		// API
		List<Doacao> donationRequests = getDonationRequestsByDonor(loggedInUserId);

		if (donationRequests != null && !donationRequests.isEmpty()) {
			System.out.println("\nYou have received the following donation requests:");

			for (Doacao donation : donationRequests) {
				System.out.println("Donation ID: " + donation.getId());
				System.out.println("Requestor ID: " + donation.getIdRequerente());
				System.out.println("Donor ID: " + donation.getIdDoador());
				System.out.println("Equipment ID: " + donation.getIdEquipamento());
				System.out.println("Date of the request: " + donation.getDataInicio());

				// Pedir a decisão do usuário
				System.out.println("1: Accept\n2: Reject");
				int choice = scanner.nextInt();
				scanner.nextLine(); // Consumir nova linha

				if (choice == 1) {
					acceptDonation(donation.getId()); // Aceitar doação
				} else {
					rejectDonation(donation.getId()); // Recusar doação
				}
			}
		} else {
			System.out.println("\nNo donation requests at the moment.");
		}
	}

	private void acceptDonation(Long donationId) {
		// Função para aceitar doação
		String url = rootAPIURLDOAC + "/accept-donation/" + donationId;
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Donation accepted successfully.");
			} else {
				System.out.println("Failed to accept donation.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void rejectDonation(Long donationId) {
		// Função para recusar doação
		String url = rootAPIURLDOAC + "/reject-donation/" + donationId;
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Donation rejected successfully.");
			} else {
				System.out.println("Failed to reject donation.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/*
	 * private void recipientMenu(Scanner scanner, Long loggedInUserId) { while
	 * (true) { System.out.println("\n===== RECIPIENT MENU =====");
	 * System.out.println("1. Register donation");
	 * System.out.println("2. View all donations by ID");
	 * System.out.println("3. Finish/cancel donation");
	 * System.out.println("4. Logout and return to login menu");
	 * 
	 * int option = scanner.nextInt(); scanner.nextLine(); // Consumir a nova linha
	 * 
	 * switch (option) { case 1: registrarPedidoDoacao(scanner, loggedInUserId);
	 * break;
	 * 
	 * case 2:
	 * System.out.print("\nEnter the requester ID to view all related donations: ");
	 * Long idRequerente = scanner.nextLong(); scanner.nextLine();
	 * getAllDonationsByRequesterId(idRequerente); break;
	 * 
	 * case 3:
	 * System.out.print("\nEnter the ID of the donation to finish or cancel: ");
	 * Long idDoacao = scanner.nextLong(); scanner.nextLine();
	 * finishOrCancelDonation(scanner, idDoacao); break;
	 * 
	 * case 4: System.out.println("Logging out and returning to login menu...");
	 * return;
	 * 
	 * default: System.out.println("Invalid option. Please try again."); } } }
	 */

	// MENUUUUUUUUUUU
	private void recipientMenu(Scanner scanner, Long loggedInUserId) {
		int option;
		do {
			System.out.println("\n===== RECIPIENT MENU =====");
			System.out.println("1. View Available Equipments");
			System.out.println("2. Register donation");
			System.out.println("3. View all donations by ID");
			System.out.println("4. Finish/cancel donation");
			System.out.println("5. Exit");

			option = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (option) {

			case 1:
				System.out.println("\nWhat type of equipment do you want to view? Choose one of the options below.\n"
						+ "1: Computers (or notebooks)\n" + "2: Accessories");
				int choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					// Listar todos os computadores
					getAllComputadores();
				} else {
					// Listar todos os acessórios
					getAllAcessorios();
				}
				break;

			case 2:
				registrarPedidoDoacao(scanner, loggedInUserId);
				break;

			case 3:
				System.out.print("\nEnter the requester ID to view all related donations: ");
				Long idRequerente = scanner.nextLong();
				scanner.nextLine();
				getAllDonationsByRequesterId(idRequerente);
				break;

			case 4:
				System.out.print("\nEnter the ID of the donation to finish or cancel: ");
				Long idDoacao = scanner.nextLong();
				scanner.nextLine();
				finishOrCancelDonation(scanner, idDoacao);
				break;

			case 5:
				System.out.println("Exiting Recipient Menu...");
				break;

			default:
				System.out.println("Invalid option. Please try again.");
			}
		} while (option != 5);
	}

	private void registrarPedidoDoacao(Scanner scanner, Long loggedInUserId) {
		System.out.print("Enter the ID of the equipment you want to request: ");
		Long idEquipamento = scanner.nextLong();
		scanner.nextLine(); // Consumir a linha restante

		System.out.print("Enter the ID of the donor: ");
		Long idDoador = scanner.nextLong();
		scanner.nextLine();

		String url = rootAPIURLDOAC + "/registrar" + "?idRequerente=" + loggedInUserId + "&idDoador=" + idDoador
				+ "&idEquipamento=" + idEquipamento;

		try {
			ResponseEntity<Doacao> response = restTemplate.postForEntity(url, null, Doacao.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Donation request successfully registered!");
				System.out.println(response.getBody());
			} else {
				System.out.println("Failed to register donation request.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void getAllDonationsByRequesterId(Long idRequerente) {
		String url = rootAPIURLDOAC + "/all-by-requester-id?idRequerente=" + idRequerente;

		try {
			ResponseEntity<Doacao[]> response = restTemplate.getForEntity(url, Doacao[].class);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				Doacao[] donations = response.getBody();
				System.out.println("\nDonations associated with requester ID " + idRequerente + ":");
				for (Doacao doacao : donations) {
					System.out.println(doacao);
				}
			} else {
				System.out.println("No donations found for the provided requester ID.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void finishOrCancelDonation(Scanner scanner, Long idDoacao) {
		System.out.println("\nWhat action would you like to take for this donation?");
		System.out.println("1: Finish Donation\n2: Cancel Donation");
		int action = scanner.nextInt();
		scanner.nextLine();

		String actionUrl = (action == 1) ? "/finalizar/" : "/cancelar/";
		String url = rootAPIURLDOAC + actionUrl + idDoacao;

		try {
			ResponseEntity<Doacao> response = restTemplate.exchange(url, HttpMethod.PUT, null, Doacao.class);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				System.out
						.println((action == 1 ? "Donation successfully finished!" : "Donation successfully canceled!"));
				System.out.println(response.getBody());
			} else {
				System.out.println("Failed to update the donation status.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/*
	 * public static void main(String[] args) { RestApi5ClientApplication myApp =
	 * new RestApi5ClientApplication(); Scanner scanner = new Scanner(System.in);
	 * 
	 * // while (true) { int option; do {
	 * System.out.println("\n===== MAIN MENU =====");
	 * System.out.println("1. Register"); System.out.println("2. Login");
	 * System.out.println("3. Exit");
	 * 
	 * option = scanner.nextInt(); scanner.nextLine(); // Consumir a nova linha
	 * 
	 * switch (option) { case 1: myApp.registerUser(scanner); break; case 2:
	 * myApp.login(scanner);
	 * System.out.println("\nYou have been logged out. Returning to Main Menu...");
	 * break; case 3: System.out.println("Exiting..."); break; default:
	 * System.out.println("Invalid option. Try again."); } } while (option != 3);
	 * 
	 * }
	 */

}
