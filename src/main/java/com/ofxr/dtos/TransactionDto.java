package com.ofxr.dtos;

public class TransactionDto {
    private String transactionId;
    private String amount;
    private String date;

    // Construtor
    public TransactionDto(String transactionId, String amount, String date) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
