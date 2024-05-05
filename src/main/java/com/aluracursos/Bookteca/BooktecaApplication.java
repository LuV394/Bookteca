package com.aluracursos.Bookteca;

import com.aluracursos.Bookteca.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.DoubleSummaryStatistics;

@SpringBootApplication
public class BooktecaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BooktecaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.ejecutaMenu();
	}


}
