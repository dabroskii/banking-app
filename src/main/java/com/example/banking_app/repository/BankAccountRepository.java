package com.example.banking_app.repository;

import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByUser(User user);
}

