package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Purchase;
import com.example.demo.entities.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CustomerService {

    public List<Customer> listAll();
    public Customer save(Customer customer);
    public void delete(Long id);

    public Customer findById(Long id);

    /////////////////

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long id);
    BigDecimal calculateBalance(Long customerId);
    List<Purchase> getCustomerPurchases(Long customerId);
    List<Transaction> getCustomerTransactions(Long customerId);
    boolean isCreditLimitExceeded(Long customerId, BigDecimal amount);
    void applyLatePaymentInterest(Long customerId);
    void restrictCreditIfOverdue(Long customerId);
    List<Customer> getCustomersByPaymentDate(LocalDate paymentDate);
    List<Purchase> getPurchasesWithInterest(Long customerId);

}
