package com.example.banking_app.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ScheduledTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "AccountID", nullable = false) // Match the exact case from MySQL
    private BankAccount account;

    private Long receivingAccountId;
    private Date date;
    private Double transactionAmount;
    private String comment;

    // Getters and setters...
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public Long getReceivingAccountId() {
        return receivingAccountId;
    }

    public void setReceivingAccountId(Long receivingAccountId) {
        this.receivingAccountId = receivingAccountId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
