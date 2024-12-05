package com.ofxr.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    private String transactionCode;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private String transactionType;

    // Construtor
    public TransactionDto(String transactionCode, BigDecimal amount, LocalDateTime date, String description, String transactionType) {
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.transactionType = transactionType;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionId(String transactionCode) {
        this.transactionCode = transactionCode;
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
