package net_yaya.ebank_service.repository;

import net_yaya.ebank_service.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> { }