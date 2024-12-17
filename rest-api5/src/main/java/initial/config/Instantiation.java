package initial.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.upt.lp.rest_api5.model.UserType;
import com.upt.lp.rest_api5.model.Utilizador;
import com.upt.lp.rest_api5.repository.UtilizadorRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UtilizadorRepository utilizadorRepository;
	@Override
	public void run(String... args) throws Exception {
		
				utilizadorRepository.deleteAll();
		
			//if (utilizadorRepository.count() == 0) {
				Utilizador adm1 = new Utilizador(null,"Carlos Freitas","carlosadm@gmail.com", "Password123", UserType.MANAGER);
				Utilizador adm2 = new Utilizador(null,"Carlos Freitas","carlosadm@gmail.com", "Password123", UserType.MANAGER);
				//utilizadorRepository.save(adm1);
				
				utilizadorRepository.saveAll(Arrays.asList(adm1, adm2));
				System.out.println("Duas pessoas foram inicializadas no banco de dados.");
			
			
	}
}
