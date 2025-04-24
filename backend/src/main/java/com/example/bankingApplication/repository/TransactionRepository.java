package com.example.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankingApplication.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
}