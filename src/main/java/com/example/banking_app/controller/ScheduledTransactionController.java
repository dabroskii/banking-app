package com.example.banking_app.controller;

import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.ScheduledTransaction;
import com.example.banking_app.repository.BankAccountRepository;
import com.example.banking_app.repository.ScheduledTransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class ScheduledTransactionController {

    private final ScheduledTransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public ScheduledTransactionController(ScheduledTransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getTransactions(@PathVariable Long accountId) {
        System.out.println("Fetching transactions for accountId: " + accountId);

        Optional<BankAccount> accountOpt = bankAccountRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            System.out.println("Found account: " + accountOpt.get());
        } else {
            System.err.println("No account found for accountId: " + accountId);
        }

        BankAccount account = accountOpt.orElseThrow(() -> new IllegalArgumentException("Account not found"));

        List<ScheduledTransaction> transactions = transactionRepository.findByAccount(account);
        System.out.println("Found transactions: " + transactions);

        return ResponseEntity.ok(transactions);
    }



    @PostMapping("/{accountId}")
    public ResponseEntity<?> createTransaction(@PathVariable Long accountId, @RequestBody ScheduledTransaction transaction) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        transaction.setAccount(account);
        transactionRepository.save(transaction);
        return ResponseEntity.status(201).body("Transaction created successfully!");
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        ScheduledTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        transactionRepository.delete(transaction);
        return ResponseEntity.ok("Transaction deleted successfully!");
    }
}
