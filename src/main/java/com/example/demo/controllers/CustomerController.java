package com.example.demo.controllers;


import com.example.demo.entities.Customer;
import com.example.demo.entities.Purchase;
import com.example.demo.entities.Transaction;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "**")
public class CustomerController {

    @Autowired
    CustomerService customerService;



    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        /*
        List<Customer> customers = customerService.listAll();
        for(Customer customer: customers ){
            customer.setReviews(null);
            customer.setBookings(null);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

         */

        List<Customer> customers = customerService.listAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getACustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.save(customer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/customers/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id) {
        Customer foundCustomer = customerService.findById(id);
        if (customer.getName()!=null) {
            foundCustomer.setName(customer.getName());
        }
        if (customer.getLastname()!=null) {
            foundCustomer.setLastname(customer.getLastname());
        }
        if (customer.getNumberPhone()!=null){
            foundCustomer.setNumberPhone(customer.getNumberPhone());
        }
        if (customer.getEmail()!=null){
            foundCustomer.setEmail(customer.getEmail());
        }
        if (customer.getAddress()!=null) {
            foundCustomer.setAddress(customer.getAddress());
        }
        if (customer.getDni()!=null) {
            foundCustomer.setDni(customer.getDni());
        }
        Customer newCustomer = customerService.save(foundCustomer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> calculateBalance(@PathVariable("id") Long id) {
        BigDecimal balance = customerService.calculateBalance(id);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<Purchase>> getCustomerPurchases(@PathVariable("id") Long id) {
        List<Purchase> purchases = customerService.getCustomerPurchases(id);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getCustomerTransactions(@PathVariable("id") Long id) {
        List<Transaction> transactions = customerService.getCustomerTransactions(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/check-credit-limit/{id}")
    public ResponseEntity<Boolean> checkCreditLimit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        boolean isExceeded = customerService.isCreditLimitExceeded(id, amount);
        return new ResponseEntity<>(isExceeded, HttpStatus.OK);
    }

    //Puede ser o no
    @PostMapping("/apply-late-payment-interest/{id}")
    public ResponseEntity<Void> applyLatePaymentInterest(@PathVariable Long id) {
        customerService.applyLatePaymentInterest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/restrict-credit-if-overdue/{id}")
    public ResponseEntity<Void> restrictCreditIfOverdue(@PathVariable Long id) {
        customerService.restrictCreditIfOverdue(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
/*
    @GetMapping("/payment-date/{date}")
    public ResponseEntity<List<Customer>> getCustomersByPaymentDate(@PathVariable String date) {
        LocalDate paymentDate = LocalDate.parse(date);
        List<Customer> customers = customerService.getCustomersByPaymentDate(paymentDate);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

*/
}
