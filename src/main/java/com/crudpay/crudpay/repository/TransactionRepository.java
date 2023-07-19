package com.crudpay.crudpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudpay.crudpay.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
