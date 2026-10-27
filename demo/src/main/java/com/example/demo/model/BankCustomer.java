package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
@Entity
public class BankCustomer {

    @Id
    @Column
    long accountNumber;

    @Column
    String name;

    @Column
    long phoneNumber;

    @Column
    double balance;
}
