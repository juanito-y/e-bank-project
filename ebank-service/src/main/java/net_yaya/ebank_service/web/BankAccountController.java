package net_yaya.ebank_service.web;


import lombok.AllArgsConstructor;
import net_yaya.ebank_service.clients.CustomerRestClient;
import net_yaya.ebank_service.entities.BankAccount;
import net_yaya.ebank_service.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class BankAccountController {
    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    @GetMapping
    public List<BankAccount> all() {
        List<BankAccount> accounts = accountRepository.findAll();
        accounts.forEach(a -> a.setCustomer(customerRestClient.findCustomerById(a.getCustomerId())));
        return accounts;
    }

    @GetMapping("/{id}")
    public BankAccount byId(@PathVariable String id) {
        BankAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setCustomer(customerRestClient.findCustomerById(account.getCustomerId()));
        return account;
    }
}