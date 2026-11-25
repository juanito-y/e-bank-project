package net.yaya.customer_service;

import net.yaya.customer_service.entities.Customer;
import net.yaya.customer_service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository repo) {
		return args -> {
			repo.save(Customer.builder().firstName("Yasmine").lastName("Ghissassi").email("yasmine@mail.com").build());
			repo.save(Customer.builder().firstName("Sara").lastName("Alami").email("sara@mail.com").build());
			repo.save(Customer.builder().firstName("Omar").lastName("Benani").email("omar@mail.com").build());
		};
	}
}