package com.example.bankingApplication.dto;

import java.time.LocalDateTime;
import java.util.List;


public class TransactionResponse {
	
	private double amount;
	 private LocalDateTime date;
	
	 public TransactionResponse(double amount, LocalDateTime date) {
		this.amount = amount;
		this.date = date;
	}
	 
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	 
	 
	    
}
