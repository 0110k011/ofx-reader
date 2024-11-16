package com.ofxr.dtos;

import java.util.List;

public class AccountStatementDto {

    private String organization;
    private String currency;
    private String bankId;
    private String branchId;
    private String accountId;
    private String accountType;
    private String dateStart;
    private String dateEnd;
    private List<TransactionDto> transactions;

    public AccountStatementDto(String organization, String currency, String bankId, String branchId,
                               String accountId, String accountType, String dateStart, String dateEnd,
                               List<TransactionDto> transactions) {
        this.organization = organization;
        this.currency = currency;
        this.bankId = bankId;
        this.branchId = branchId;
        this.accountId = accountId;
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

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}