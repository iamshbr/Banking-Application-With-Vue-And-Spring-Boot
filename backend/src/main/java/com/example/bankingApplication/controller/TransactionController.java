package com.example.bankingApplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingApplication.dto.TransactionResponse;
import com.example.bankingApplication.model.LoanRequest;
import com.example.bankingApplication.model.Transaction;
import com.example.bankingApplication.model.TransferRequest;
import com.example.bankingApplication.model.User;
import com.example.bankingApplication.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transferMoney")
	public ResponseEntity<Object> transferMoney(@RequestBody TransferRequest tranferRequest) {
		try {
			Object transactionResponse = transactionService.transferMoney(tranferRequest);
			
			if (transactionResponse != null && transactionResponse instanceof TransactionResponse) {
				return ResponseEntity.ok().header("Content-Type", "application/json").body(transactionResponse);
			}else if(transactionResponse instanceof String){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(transactionResponse.toString());
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	
	@PostMapping("/loanRequest")
	public ResponseEntity<Object> loanRequest(@RequestBody LoanRequest loanRequest) {
		try {
			Object loanResponse = transactionService.loanRequest(loanRequest);
			if (loanResponse != null && loanResponse instanceof TransactionResponse) {
				return ResponseEntity.ok().header("Content-Type", "application/json").body(loanResponse);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Some Error Occurred");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
