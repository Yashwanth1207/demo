package com.example.demo.controller;

import com.example.demo.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class WithdrawController {

    private  BankAccountService bankAccountService;

//    public WithdrawController(BankAccountService bankAccountService) {
//        this.bankAccountService = bankAccountService;
//    }

    @PostMapping("/{accountNumber}/withdraw")
    public String withdraw(@PathVariable long accountNumber,@RequestParam double amount) {
        return bankAccountService.withdraw(accountNumber, amount);
    }
}