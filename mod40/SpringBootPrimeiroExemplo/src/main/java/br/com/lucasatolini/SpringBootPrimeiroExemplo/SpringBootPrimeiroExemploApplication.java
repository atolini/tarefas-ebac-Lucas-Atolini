package br.com.lucasatolini.SpringBootPrimeiroExemplo;

import br.com.lucasatolini.SpringBootPrimeiroExemplo.domain.Cliente;
import br.com.lucasatolini.SpringBootPrimeiroExemplo.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "br.com.lucasatolini.SpringBootPrimeiroExemplo.repository")
@EntityScan("br.com.lucasatolini.*")
@ComponentScan(basePackages = "br.com.lucasatolini")
public class SpringBootPrimeiroExemploApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	private static final Logger log = LoggerFactory.getLogger(SpringBootPrimeiroExemploApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPrimeiroExemploApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("StartApplication...");
		Cliente c = Cliente.builder()
				.cidade("SP")
				.cpf(100_100_100_10L)
				.email("teste@teste.com.br")
				.end("End")
				.estado("SP")
				.nome("Teste Spring Boot")
				.numero(383)
				.tel(9_0000_0000L)
				.build();

		this.clienteRepository.save(c);
	}
}
