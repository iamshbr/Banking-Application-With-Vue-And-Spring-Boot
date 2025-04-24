package com.example.bankingApplication.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bankingApplication.dto.TransactionResponse;
import com.example.bankingApplication.dto.UserResponse;
import com.example.bankingApplication.model.AuthRequest;
import com.example.bankingApplication.model.LoanRequest;
import com.example.bankingApplication.model.Transaction;
import com.example.bankingApplication.model.TransferRequest;
import com.example.bankingApplication.model.User;
import com.example.bankingApplication.repository.TransactionRepository;
import com.example.bankingApplication.repository.UserRepository;

@Service
public class TransactionService {

	@Autowired
    private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	
	public Object transferMoney(TransferRequest transferRequest) {
        try {
        	if(transferRequest != null) {
        		User sender = userRepository.findByUsername(transferRequest.getSender());
        		User receiver = userRepository.findByUsername(transferRequest.getReceiver());
        		if(sender != null && receiver != null) {
        			double total = sender.getTransactions().stream().map(Transaction::getAmount).reduce(0.0, Double::sum);
        			if(total > 0) {
            			Transaction receiverTrans = new Transaction();
            			receiverTrans.setAmount(transferRequest.getAmount());
            			receiverTrans.setUser(receiver);
            			receiverTrans.setDate(LocalDateTime.now());
            			
            			Transaction senderTrans = new Transaction();
            			senderTrans.setAmount(-transferRequest.getAmount());
            			senderTrans.setUser(sender);
            			senderTrans.setDate(LocalDateTime.now());
            			
            			transactionRepository.save(receiverTrans);
            			transactionRepository.save(senderTrans);
            			
            			return new TransactionResponse(senderTrans.getAmount(), senderTrans.getDate());
        			}else {
        				throw new RuntimeException("Insufficient Balance");
        			}
        		}else {
        			throw new RuntimeException("Receiver cannot be found");
        		}
        	}
        }catch(RuntimeException e) {
        	e.printStackTrace();
        	return e.getMessage();
        }
        return null;
    }
	
	public TransactionResponse loanRequest(LoanRequest loanRequest) {
        try {
        	if(loanRequest != null) {
        		User requestor = userRepository.findByUsername(loanRequest.getRequestor());
        		if(requestor != null) {
        			Transaction trans = new Transaction();
        			trans.setAmount(loanRequest.getAmount());
        			trans.setUser(requestor);
        			trans.setDate(LocalDateTime.now());
        			transactionRepository.save(trans);
        			return new TransactionResponse(trans.getAmount(), trans.getDate());
        		}
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
	
}
