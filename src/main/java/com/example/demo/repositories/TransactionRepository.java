package com.example.demo.repositories;

import com.example.demo.entities.PurchaseDetail;
import com.example.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerId(Long customerId);

}
