package com.example.demo.entities;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="payments")

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
/*
    private Customer customer;
    private BigDecimal currentBalance;
    private List<Purchase> purchases;
*/
    @ManyToOne
    @JoinColumn(name = "customer_Id")
    private Customer customer;

}
