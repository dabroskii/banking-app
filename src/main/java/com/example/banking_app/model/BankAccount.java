package com.example.banking_app.model;

import jakarta.persistence.*;

@Entity
public class BankAccount {
    @Id
    @Column(name = "AccountID") // Match the exact case from MySQL
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false) // Match the exact case for the foreign key
    private User user;

    private String accountType;
    private Double accountBalance;

    // Getters and Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
