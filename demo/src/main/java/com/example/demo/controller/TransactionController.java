
package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

//    // Get transactions for a specific account
//    @GetMapping("/{accountNumber}/history")
//    public List<Transaction> getTransactionHistory(
//            @PathVariable Long accountNumber) {
//
//        List<Transaction> transactions =
//                transactionService.getAllTransaction(accountNumber);
//
//        if (transactions.isEmpty()) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,"No transactions found for account number: "+ accountNumber
//            );
//        }
//
//        return transactions;
//    }

            @GetMapping("/{accountNumber}/history")
        public List<Transaction> getTransactionHistory( @PathVariable Long accountNumber) {
          //  System.out.println("HISTORY ENDPOINT REACHED: " + accountNumber);
            return transactionService.getAllTransaction(accountNumber);
        }


//    @GetMapping("/{accountNumber}/history")
//    public List<Transaction> getTransactionHistory(@PathVariable Long accountNumber) {
//        return transactionService.getAllTransaction(accountNumber);
//    }

    // Get all transactions for all accounts
    @GetMapping("/history")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
