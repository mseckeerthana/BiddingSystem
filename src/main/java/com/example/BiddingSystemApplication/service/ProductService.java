package com.example.BiddingSystemApplication.service;

import java.util.List;

import com.example.BiddingSystemApplication.model.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    Product endBiddingForProduct(Long productId);
}