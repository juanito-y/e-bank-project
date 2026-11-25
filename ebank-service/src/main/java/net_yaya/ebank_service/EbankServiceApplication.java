package net_yaya.ebank_service;

import net_yaya.ebank_service.entities.BankAccount;
import net_yaya.ebank_service.enums.AccountType;
import net_yaya.ebank_service.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository repo) {
		return args -> {
			for (long i = 1; i <= 3; i++) {
				repo.save(BankAccount.builder()
						.accountId(UUID.randomUUID().toString())
						.balance(10000 * i)
						.createdAt(LocalDate.now())
						.type(i % 2 == 0 ? AccountType.CURRENT : AccountType.SAVING)
						.customerId(i)
						.build());
			}
		};
	}
}