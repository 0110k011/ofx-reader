package com.ofxr.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private String transactionType;

    // Construtor
    public TransactionDto(String transactionId, BigDecimal amount, LocalDateTime date, String description, String transactionType) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
