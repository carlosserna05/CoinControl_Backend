package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import com.example.demo.entities.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long>{

}
