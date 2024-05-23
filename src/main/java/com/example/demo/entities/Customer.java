package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String numberPhone;
    private String email;
    private String address;

    private BigDecimal creditLimit;
    private LocalDate monthlyPaymentDate;
    private BigDecimal balance;
    private BigDecimal defaultInterest;

    @OneToOne
    @JoinColumn(name = "user_Id") // viene de la tabla USER
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;

    @OneToMany(mappedBy = "customer")
    private List<Product> products;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "customer")
    private List<PurchaseDetail> purchaseDetails;


}
