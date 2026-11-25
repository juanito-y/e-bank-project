package net_yaya.ebank_service.entities;

import jakarta.persistence.*;
import lombok.*;
import net_yaya.ebank_service.enums.AccountType;
import net_yaya.ebank_service.model.Customer;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Long customerId;
    @Transient
    private Customer customer;
}