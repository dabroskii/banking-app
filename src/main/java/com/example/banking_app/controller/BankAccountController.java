package com.example.banking_app.controller;

import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.User;
import com.example.banking_app.repository.BankAccountRepository;
import com.example.banking_app.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccountController(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getBankAccounts(Principal principal) {
        // Get the authenticated user's username
        String username = principal.getName();

        // Find the User entity by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Fetch all bank accounts for the user
        List<BankAccount> accounts = bankAccountRepository.findByUser(user);

        return ResponseEntity.ok(accounts);
    }
}

