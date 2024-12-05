package com.ofxr.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class AccountStatementDto {

    private String organization;
    private String currency;
    private String bankCode;
    private String branchCode;
    private String accountCode;
    private String accountType;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private List<TransactionDto> transactions;

    public AccountStatementDto(String organization, String currency, String bankCode, String branchCode,
                               String accountCode, String accountType, LocalDateTime dateStart, LocalDateTime dateEnd,
                               List<TransactionDto> transactions) {
        this.organization = organization;
        this.currency = currency;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountCode = accountCode;
        this.accountType = accountType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.transactions = transactions;
    }
    public AccountStatementDto() {

    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchId(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}
