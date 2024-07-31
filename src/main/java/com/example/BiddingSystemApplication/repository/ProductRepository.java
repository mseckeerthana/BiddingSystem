package com.example.BiddingSystemApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BiddingSystemApplication.model.Bid;
import com.example.BiddingSystemApplication.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	 // Finds the highest bid for a specific product
    Optional<Bid> findTopByProductOrderByAmountDesc(Product product);
}
