package com.example.bankingApplication.dto;

import java.util.List;

import com.example.bankingApplication.model.Transaction;

public class UserResponse {

    private String owner;
    private List<Transaction> transactions;
    private double interestRate;
    private String pin;
    private String username;
    private String currency;
    private String locale;

    // Constructors, Getters, and Setters

    public UserResponse(String owner, List<Transaction> transactions, double interestRate, String pin, String username,String currency, String locale) {
        this.owner = owner;
        this.transactions = transactions;
        this.interestRate = interestRate;
        this.pin = pin;
        this.username = username;
        this.currency = currency;
        this.locale = locale;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}

