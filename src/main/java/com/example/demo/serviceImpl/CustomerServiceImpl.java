package com.example.demo.serviceImpl;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Purchase;
import com.example.demo.entities.Transaction;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PurchaseRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.UserSecurityRepository;
import com.example.demo.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<Customer> listAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer save(Customer customer) {

        /*
        if (customer.getName()==null || customer.getName().isEmpty()) {
            throw new IncompleteDataException("name can not be null or empty");
        }*/

        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {

        Customer customer = findById(id);
        customerRepository.delete(customer);

    }

    @Override
    public Customer findById(Long id) {
        /*
        String username = userSecurityRepository.findById(id).orElse(null).getUserName();
        Customer clientFound = customerRepository.findByName(username);
        if (clientFound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(id));
        }
        return clientFound;*/

        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
    }


    /////////////////



    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public BigDecimal calculateBalance(Long customerId) {
        Customer customer = getCustomerById(customerId);
        BigDecimal balance = BigDecimal.ZERO;
        for (Purchase purchase : customer.getPurchases()) {
            if (purchase.getSalesStatus() == Purchase.SalesStatus.PENDIENTE) {
                balance = balance.add(purchase.getTotal());
            }
        }
        return balance;
    }

    @Override
    public List<Purchase> getCustomerPurchases(Long customerId) {
        return purchaseRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Transaction> getCustomerTransactions(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    @Override
    public boolean isCreditLimitExceeded(Long customerId, BigDecimal amount) {
        Customer customer = getCustomerById(customerId);
        BigDecimal newBalance = customer.getBalance().add(amount);
        return newBalance.compareTo(customer.getCreditLimit()) > 0;
    }

    @Override
    public void applyLatePaymentInterest(Long customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer.getMonthlyPaymentDate().isBefore(LocalDate.now())) {
            BigDecimal interest = customer.getBalance().multiply(customer.getDefaultInterest());
            customer.setBalance(customer.getBalance().add(interest));
            customerRepository.save(customer);
        }
    }

    @Override
    public void restrictCreditIfOverdue(Long customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer.getMonthlyPaymentDate().isBefore(LocalDate.now()) && customer.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            // Logic to restrict credit
            customerRepository.save(customer);
        }
    }

    @Override
    public List<Customer> getCustomersByPaymentDate(LocalDate paymentDate) {
        return customerRepository.findByMonthlyPaymentDate(paymentDate);
    }

    @Override
    public List<Purchase> getPurchasesWithInterest(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getPurchases().stream()
                .filter(purchase -> purchase.getSalesStatus() == Purchase.SalesStatus.PENDIENTE)
                .collect(Collectors.toList());
    }







}
