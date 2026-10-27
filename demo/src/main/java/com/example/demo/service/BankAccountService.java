package com.example.demo.service;

import com.example.demo.model.BankCustomer;
import com.example.demo.repository.BankDBRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    TransactionService transactionService;
    BankDBRepository bankDBRepository;

        public BankAccountService(BankDBRepository bankDBRepository, TransactionService transactionService){
            this.bankDBRepository =bankDBRepository;
            this.transactionService = transactionService;
        }


        //  Account creationn
        public BankCustomer createAccount(BankCustomer customer) {

            if (bankDBRepository.existsById(customer.getAccountNumber())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already exists");
            }

            return bankDBRepository.save(customer);
        }



        //Get aLl Account
        public List<BankCustomer> getAllAccounts(){
            return bankDBRepository.findAll();
        }


                //Find account by account numbers

        public Optional<BankCustomer> findByID(Long accountNumber){
            return bankDBRepository.findById(accountNumber);
        }


        //update account detials
        public String updateAccount(BankCustomer newCustomer){
            Optional<BankCustomer> obj=findByID(newCustomer.getAccountNumber());
            if(obj.isPresent()){
                BankCustomer existingCustomer=obj.get();

                existingCustomer.setName(newCustomer.getName());
                existingCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
                existingCustomer.setBalance(newCustomer.getBalance());

                bankDBRepository.save(existingCustomer);
                return "Account updated successfully";
            }
            return "Account not found";
        }

        // Delete   by             Acccount Number
        public String deleteAccount(long accountNumber){
            Optional<BankCustomer> obj=findByID(accountNumber);

            if(obj.isPresent()){
                bankDBRepository.delete(obj.get());
                        return "Account deleted successfully";
            }
            return "account not found";
        }

// get balace by account number
    public double getBalance(long accountNumber) {
        Optional<BankCustomer> customer =bankDBRepository.findById(accountNumber);

        if (customer.isPresent()) {
            return customer.get().getBalance();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"
        );
    }


    // Deposit money
    @Transactional
    public String deposit(long accountNumber, double amount) {

        validateAmount(amount);

        BankCustomer customer = findByID(accountNumber)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"
                        ));

        double newBalance = customer.getBalance() + amount;

        if (!Double.isFinite(newBalance)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid deposit amount"
            );
        }

        customer.setBalance(newBalance);
        bankDBRepository.save(customer);

        transactionService.saveTransaction(
                accountNumber,
                "DEPOSIT",
                amount,
                newBalance
        );

        return "Deposit successful. New balance: " + newBalance;
    }





    // Withdraw money
    @Transactional
    public String withdraw(long accountNumber, double amount) {

        validateAmount(amount);

        BankCustomer customer = findByID(accountNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"
                        ));

        if (customer.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insufficient balance"
            );
        }

        double newBalance = customer.getBalance() - amount;

        customer.setBalance(newBalance);
        bankDBRepository.save(customer);

        transactionService.saveTransaction(
                accountNumber,
                "WITHDRAW",
                amount,
                newBalance
        );

        return "Withdrawal successful. New balance: " + newBalance;
    }

    // Validate deposit and withdrawal amounts
    private void validateAmount(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Amount must be greater than zero"
            );
        }
    }


}
