package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionDBRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class TransactionService {


    TransactionDBRepository transactionDBRepository;

    public TransactionService(TransactionDBRepository transactionDBRepository) {
        this.transactionDBRepository = transactionDBRepository;
    }

    // Save transaction
    public Transaction saveTransaction(Long accountNumber, String transactionType, double amount, double balanceAfterTransaction) {

        Transaction transaction = new Transaction();

        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionamount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setBalanceAfterTransaction(balanceAfterTransaction);

        return transactionDBRepository.save(transaction);

    }


    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionDBRepository.findAll();
    }



    // Get transaction history for a specific account
    public List<Transaction> getAllTransaction(Long accountNumber) {
        List<Transaction> transactions =transactionDBRepository.findByAccountNumber(accountNumber);

        if (transactions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.OK,"No transactions found for account number: " + accountNumber
            );
        }

        return transactions;
    }



}
