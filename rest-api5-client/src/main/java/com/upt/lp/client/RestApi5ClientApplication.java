package com.upt.lp.client;

import java.awt.print.Book;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.upt.lp.rest_api5.model.Acessorio;
import com.upt.lp.rest_api5.model.Computador;
import com.upt.lp.rest_api5.model.Utilizador;

@SpringBootApplication
public class RestApi5ClientApplication {

	private RestTemplate restTemplate = new RestTemplate();

	private String rootAPIURLACE = "http://localhost:8080/api/acessorios";

	private String rootAPIURLCOMP = "http://localhost:8080/api/computadores";

	private String rootAPIURLUSER = "http://localhost:8080/api/utilizadores";

	public void registerUser(Scanner scanner) {
		Utilizador utilizador = new Utilizador();

		System.out.print("\nEnter your username: ");
		String username = scanner.nextLine();
		utilizador.setNome(username);

		System.out.print("\nEnter your email: ");
		String email = scanner.nextLine();
		utilizador.setEmail(email);

		System.out.print("\nEnter your password: ");
		String password = scanner.nextLine();
		utilizador.setSenha(password);

		System.out.print("\nEnter your type (DONOR/RECIPIENT): ");
		String tipo = scanner.nextLine();
		utilizador.setUserType(tipo);

		// Enviar requisição POST para registar o utilizador
		ResponseEntity<Utilizador> response = restTemplate.postForEntity(rootAPIURLUSER + "/register", utilizador,
				Utilizador.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			System.out.println("User registered successfully!");
		} else {
			System.out.println("Failed to register user.");
		}
	}

	/*
	 * // Função para fazer login public User loginUser(Scanner scanner) {
	 * System.out.print("Enter your email: "); String email = scanner.nextLine();
	 * 
	 * System.out.print("Enter your password: "); String password =
	 * scanner.nextLine();
	 * 
	 * // Enviar para o servidor via GET para validar o login ResponseEntity<User>
	 * response = restTemplate.getForEntity(rootAPIURLUSER + "/login?email=" + email
	 * + "&password=" + password, User.class);
	 * 
	 * if (response.getStatusCode().is2xxSuccessful()) { User user =
	 * response.getBody(); System.out.println("Login successful. Welcome " +
	 * user.getName()); return user; } else {
	 * System.out.println("Invalid login credentials."); return null; } }
	 */

	/*
	 * // Método para fazer login private void login(Scanner scanner) {
	 * System.out.print("Enter your email: "); String email = scanner.nextLine();
	 * 
	 * System.out.print("Enter your password: "); String password =
	 * scanner.nextLine();
	 * 
	 * String loginUrl = rootAPIURLUSER + "/login?email=" + email + "&senha=" +
	 * password;
	 * 
	 * try { ResponseEntity<String> response = restTemplate.postForEntity(loginUrl,
	 * null, String.class);
	 * 
	 * if (response.getStatusCode().is2xxSuccessful()) {
	 * System.out.println(response.getBody()); } else {
	 * System.out.println("Invalid login credentials."); } } catch (Exception e) {
	 * System.out.println("Error: " + e.getMessage()); } }
	 */

	/*
	 * private void login(Scanner scanner) { System.out.print("Enter your email: ");
	 * String email = scanner.nextLine();
	 * 
	 * System.out.print("Enter your password: "); String password =
	 * scanner.nextLine();
	 * 
	 * String loginUrl = rootAPIURLUSER + "/login?email=" + email + "&senha=" +
	 * password;
	 * 
	 * try { ResponseEntity<Utilizador> response =
	 * restTemplate.postForEntity(loginUrl, null, Utilizador.class);
	 * 
	 * if (response.getStatusCode().is2xxSuccessful()) { Utilizador loggedInUser =
	 * response.getBody(); System.out.println("Login successful! Welcome, " +
	 * loggedInUser.getNome()); if
	 * ("DONOR".equalsIgnoreCase(loggedInUser.getUserType())) { donorMenu(scanner);
	 * } else if ("RECIPIENT".equalsIgnoreCase(loggedInUser.getUserType())) {
	 * recipientMenu(scanner); } else { System.out.println("Invalid user type."); }
	 * } else { System.out.println("Invalid login credentials."); } } catch
	 * (Exception e) { System.out.println("Error: " + e.getMessage()); } }
	 */
	
	private void login(Scanner scanner) {
	    System.out.print("Enter your email: ");
	    String email = scanner.nextLine();

	    System.out.print("Enter your password: ");
	    String password = scanner.nextLine();

	    String loginUrl = rootAPIURLUSER + "/login?email=" + email + "&senha=" + password;

	    try {
	        ResponseEntity<Utilizador> response = restTemplate.postForEntity(loginUrl, null, Utilizador.class);

	        if (response.getStatusCode().is2xxSuccessful()) {
	            Utilizador loggedInUser = response.getBody();
	            System.out.println("Login successful! Welcome, " + loggedInUser.getNome());

	            // Menu com base no tipo de usuário
	            if ("DONOR".equalsIgnoreCase(loggedInUser.getUserType())) {
	                donorMenu(scanner);
	            } else if ("RECIPIENT".equalsIgnoreCase(loggedInUser.getUserType())) {
	                recipientMenu(scanner);
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
				String newConservation = scanner.nextLine();
				if (!newConservation.isEmpty())
					acessorio.setEstadoConservacao(newConservation);

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

	public void updateComputador(Scanner scanner) {
	    System.out.print("\nEnter the ID of the computer to update: ");
	    Long id = scanner.nextLong();
	    scanner.nextLine(); 

	    try {
	        
	        ResponseEntity<Computador> response = restTemplate.getForEntity(rootAPIURLCOMP + "/" + id, Computador.class);

	        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	            Computador computador = response.getBody();

	            System.out.println("Current details of the computer: " + computador.toString());

	            System.out.print("\nEnter the new type of computer (DESKTOP or LAPTOP) (leave empty to keep current): ");
	            String newType = scanner.nextLine();
	            if (!newType.isEmpty()) computador.setType(newType);

	            System.out.print("\nEnter new RAM capacity (EX: 4GB, 8GB, 16GB, 32GB or more) (leave empty to keep current): ");
	            String newRam = scanner.nextLine();
	            if (!newRam.isEmpty()) computador.setRam(newRam);

	            System.out.print("\nEnter the new drive type (HDD or SSD) (leave empty to keep current): ");
	            String newDrive = scanner.nextLine();
	            if (!newDrive.isEmpty()) computador.setDisco(newDrive);

	            System.out.print("\nEnter the new processor type (EX: Intel, AMD, Apple Silicon) (leave empty to keep current): ");
	            String newProcessor = scanner.nextLine();
	            if (!newProcessor.isEmpty()) computador.setProcessador(newProcessor);

	            System.out.print("\nEnter a new name for the computer (leave empty to keep current): ");
	            String newName = scanner.nextLine();
	            if (!newName.isEmpty()) computador.setNome(newName);

	            System.out.print("\nEnter a new year for the computer (leave empty to keep current): ");
	            String newYear = scanner.nextLine();
	            if (!newYear.isEmpty()) computador.setAno(newYear);

	            System.out.print("\nEnter a new brand for the computer (EX: Dell, HP, Lenovo, Apple, Acer, ASUS) (leave empty to keep current): ");
	            String newBrand = scanner.nextLine();
	            if (!newBrand.isEmpty()) computador.setBrand(newBrand);

	            System.out.print("\nEnter the new conservation status of the computer (leave empty to keep current): ");
	            String newConservation = scanner.nextLine();
	            if (!newConservation.isEmpty()) computador.setEstadoConservacao(newConservation);

	           
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

	private void donorMenu(Scanner scanner) {
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
			System.out.println("3. Edit Equipment");
			System.out.println("4. Delete Equipment");

			int option = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (option) {
			case 1:
				System.out.println("\nWhat type of equipment will be add? Choose one of the options below.\n"
						+ "1: Computer (or notebook)\n" + "2: Accessories");
				int choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					createComputador(scanner);
					break;
				} else {
					createAcessorio(scanner);
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
									+ "1: All computers. \n" + "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
						getAllComputadores();
						break;
					} else {
						System.out.println("\nEnter the ID of the computer you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						getComputadorById(idSearch);
						break;
					}
				} else {
					System.out.println(
							"\nDo you want to search for all accessories or for a specific one? Choose one of the options below.\n"
									+ "1: All accessories. \n" + "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
						getAllAcessorios();
						break;
					} else {
						System.out.print("\nEnter the ID of the accessory you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						getAcessorioById(idSearch);
						break;
					}

				}
				
			case 3: 
			    System.out.println("\nWhat type of equipment do you want to update? Choose one of the options below.\n"
			                       + "1: Computer (or notebook)\n"
			                       + "2: Accessories");
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

				/*
				 * case 3: System.out.print("Digite o ID do acessório a ser deletado: "); Long
				 * acessorioId = scanner.nextLong(); myApp.deleteAcessorio(acessorioId); break;
				 * case 6: System.out.print("Digite o ID do computador a ser deletado: "); Long
				 * computadorId = scanner.nextLong(); myApp.deleteComputador(computadorId);
				 * break; case 7: System.out.println("Encerrando o programa...");
				 * scanner.close(); System.exit(0);
				 */
				
				// case update:
				
			
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	
	}
	
	private void recipientMenu(Scanner scanner) {
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

			System.out.println("\n===== RECIPIENT MENU =====");
			System.out.println("1. Add Equipment TESTE TESTE");
			System.out.println("2. Search Equipment");
			System.out.println("3. Edit Equipment");
			System.out.println("4. Delete Equipment");

			int option = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (option) {
			case 1:
				System.out.println("\nWhat type of equipment will be add? Choose one of the options below.\n"
						+ "1: Computer (or notebook)\n" + "2: Accessories");
				int choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					createComputador(scanner);
					break;
				} else {
					createAcessorio(scanner);
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
									+ "1: All computers. \n" + "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
						getAllComputadores();
						break;
					} else {
						System.out.println("\nEnter the ID of the computer you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						getComputadorById(idSearch);
						break;
					}
				} else {
					System.out.println(
							"\nDo you want to search for all accessories or for a specific one? Choose one of the options below.\n"
									+ "1: All accessories. \n" + "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
						getAllAcessorios();
						break;
					} else {
						System.out.print("\nEnter the ID of the accessory you want to search: ");
						Long idSearch = scanner.nextLong();
						scanner.nextLine();
						getAcessorioById(idSearch);
						break;
					}

				}
				
			case 3: 
			    System.out.println("\nWhat type of equipment do you want to update? Choose one of the options below.\n"
			                       + "1: Computer (or notebook)\n"
			                       + "2: Accessories");
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

				/*
				 * case 3: System.out.print("Digite o ID do acessório a ser deletado: "); Long
				 * acessorioId = scanner.nextLong(); myApp.deleteAcessorio(acessorioId); break;
				 * case 6: System.out.print("Digite o ID do computador a ser deletado: "); Long
				 * computadorId = scanner.nextLong(); myApp.deleteComputador(computadorId);
				 * break; case 7: System.out.println("Encerrando o programa...");
				 * scanner.close(); System.exit(0);
				 */
				
				// case update:
				
			
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	
	}

	

	public static void main(String[] args) {
		RestApi5ClientApplication myApp = new RestApi5ClientApplication();
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    myApp.registerUser(scanner);
                    break;
                case 2:
                	myApp.login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
		}
	
	}
	
}

		/*
		 * //myApp.updateAcessorio(); //System.out.println("Sucessfull");
		 * myApp.createAcessorio(); System.out.println("Sucessfull");
		 * myApp.getAllAcessorios(); System.out.println("Sucessfull");
		 * 
		 * myApp.getAcessorioById((long) 3); System.out.println("Sucessfull");
		 * //myApp.deleteAcessorio((long) 20); //System.out.println("Sucessfull");
		 * //myApp.getAllAcessorios(); //System.out.println("Sucessfull");
		 */

		/*while (true) {
			
			 * System.out.println("\n===== MENU =====");
			 * System.out.println("1. Criar Acessório"); --------- OK
			 * System.out.println("2. Listar Acessórios");
			 * System.out.println("3. Deletar Acessório");
			 * System.out.println("4. Criar Computador"); --------- OK
			 * System.out.println("5. Listar Computadores");
			 * System.out.println("6. Deletar Computador"); System.out.println("7. Sair");
			 * System.out.print("Escolha uma opção: ");
			 

			System.out.println("\n===== DONOR MENU =====");
			System.out.println("1. Add Equipment");
			System.out.println("2. Search Equipment");
			System.out.println("3. Edit Equipment");
			System.out.println("4. Delete Equipment");

			int option = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (option) {
			case 1:
				System.out.println("\nWhat type of equipment will be add? Choose one of the options below.\n"
						+ "1: Computer (or notebook)\n" + "2: Accessories");
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
				System.out
						.println("\nWhat type of equipment do you want to research? Choose one of the options below.\n"
								+ "1: Computer (or notebook)\n" + "2: Accessories");
				choiceEquipmentType = scanner.nextInt();
				scanner.nextLine();

				if (choiceEquipmentType == 1) {
					System.out.println(
							"\nDo you want to search for all computers or for a specific one? Choose one of the options below.\n"
									+ "1: All computers. \n" + "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
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
					System.out.println(
							"\nDo you want to search for all accessories or for a specific one? Choose one of the options below.\n"
									+ "1: All accessories. \n" + "2: A specific. ");
					int choiceResearchType = scanner.nextInt();
					scanner.nextLine();

					if (choiceResearchType == 1) {
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
			    System.out.println("\nWhat type of equipment do you want to update? Choose one of the options below.\n"
			                       + "1: Computer (or notebook)\n"
			                       + "2: Accessories");
			    choiceEquipmentType = scanner.nextInt();
			    scanner.nextLine();

			    if (choiceEquipmentType == 1) {
			        myApp.updateComputador(scanner);
			    } else {
			        myApp.updateAcessorio(scanner);
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
					myApp.deleteComputador(idToDelete);
					break;
				} else {
					System.out.print("\nEnter the ID of the accessory you want to delete: ");
					Long idToDelete = scanner.nextLong();
					scanner.nextLine();
					myApp.deleteAcessorio(idToDelete);
					break;
				}

				
				 * case 3: System.out.print("Digite o ID do acessório a ser deletado: "); Long
				 * acessorioId = scanner.nextLong(); myApp.deleteAcessorio(acessorioId); break;
				 * case 6: System.out.print("Digite o ID do computador a ser deletado: "); Long
				 * computadorId = scanner.nextLong(); myApp.deleteComputador(computadorId);
				 * break; case 7: System.out.println("Encerrando o programa...");
				 * scanner.close(); System.exit(0);
				 
				
				// case update:
				
			
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}*/

