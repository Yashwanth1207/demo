package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.BankAccountService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class DepositController {
    @Autowired
    private  BankAccountService bankAccountService;
  //  TransactionService transactionService;

//    public DepositController(BankAccountService bankAccountService,  TransactionService transactionService) {
//        this.bankAccountService = bankAccountService;
//      //  this.transactionService = transactionService;
//    }


            //deposit money
    @PostMapping("/{accountNumber}/deposit")
    public String deposit(@PathVariable long accountNumber, @RequestParam double amount) {

        return bankAccountService.deposit(accountNumber, amount);
    }


//    // get transaction history by account number
//    @GetMapping("/{accountNumber}/history")
//    public List<Transaction> getTransactionHistory(@PathVariable long accountNumber){
//        return transactionService.getAllTransaction(accountNumber);
//    }



//    // Get all transactions
//    @GetMapping("/history")
//    public List<Transaction> getAllTransactions() {
//        return transactionService.getAllTransactions();
//    }
}