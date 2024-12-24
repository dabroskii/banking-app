package com.example.banking_app.repository;

import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.ScheduledTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledTransactionRepository extends JpaRepository<ScheduledTransaction, Long> {
    List<ScheduledTransaction> findByAccount(BankAccount account);
}

