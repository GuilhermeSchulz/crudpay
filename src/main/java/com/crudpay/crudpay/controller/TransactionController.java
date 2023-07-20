package com.crudpay.crudpay.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudpay.crudpay.dto.CreateTransactionDto;
import com.crudpay.crudpay.model.Transaction;
import com.crudpay.crudpay.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;

    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody final CreateTransactionDto transactionData) {
        final Transaction createdTransaction = transactionService.createTransaction(transactionData);
        return new ResponseEntity<Transaction>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable String id) {
        final Transaction transaction = transactionService.getTransaction(Long.parseLong(id));
        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }
}
