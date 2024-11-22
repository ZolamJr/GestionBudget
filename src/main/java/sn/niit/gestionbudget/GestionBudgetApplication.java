package sn.niit.gestionbudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFilter;

@SpringBootApplication
public class GestionBudgetApplication {


	public static void main(String[] args) {
		SpringApplication.run(GestionBudgetApplication.class, args);
	}

}
