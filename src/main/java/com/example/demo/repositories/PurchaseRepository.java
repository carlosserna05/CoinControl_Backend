package com.example.demo.repositories;

import com.example.demo.entities.Purchase;
import com.example.demo.entities.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByCustomerId(Long customerId);

}
