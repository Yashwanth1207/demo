package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionDBRepository
        extends JpaRepository<Transaction, Long> {

        @Query(value = "SELECT balance FROM bank WHERE account_number = :acc",nativeQuery = true)
        double getBankBalance(@Param("acc") long accountNumber);

        @Transactional
        @Modifying
        @Query(value = "SELECT * FROM `transaction` WHERE account_number = :acc ORDER BY transaction_id DESC",nativeQuery = true)
        List<Transaction> findByAccountNumber(@Param("acc") Long accountNumber);
}