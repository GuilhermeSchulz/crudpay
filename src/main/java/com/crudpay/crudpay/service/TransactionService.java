package com.crudpay.crudpay.service;

import org.springframework.stereotype.Service;

import com.crudpay.crudpay.dto.CreateTransactionDto;
import com.crudpay.crudpay.model.Transaction;
import com.crudpay.crudpay.model.User;
import com.crudpay.crudpay.repository.TransactionRepository;
import com.crudpay.crudpay.repository.UserRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(CreateTransactionDto transactionData) throws RuntimeException {

        final User foundPayer = userRepository.findById(transactionData.getPayer_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        final User foundPayee = userRepository.findById(transactionData.getPayee_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        final float PayeeCurrentBalance = foundPayee.getBalance();
        final float PayerCurrentBalance = foundPayer.getBalance();

        foundPayee.setBalance(PayeeCurrentBalance + transactionData.getValue());
        foundPayer.setBalance(PayerCurrentBalance - transactionData.getValue());

        final Transaction transactionSave = new Transaction(foundPayer, foundPayee,
                transactionData.getValue());
        return transactionRepository.save(transactionSave);
    }

    public Transaction getTransaction(final long id) throws RuntimeException {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    };

}
