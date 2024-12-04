package com.ofxr.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime date;

    // Construtor
    public TransactionDto(String transactionId, BigDecimal amount, LocalDateTime date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
    }

    // Getters e Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
