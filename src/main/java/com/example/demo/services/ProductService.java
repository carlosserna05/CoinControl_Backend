package com.example.demo.services;

import com.example.demo.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAll();
    Product save(Product product);
    void delete(Long id);
    Product findById(Long id);

}
