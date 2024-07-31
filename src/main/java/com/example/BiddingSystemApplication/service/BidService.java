package com.example.BiddingSystemApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiddingSystemApplication.exception.InvalidBidAmountException;
import com.example.BiddingSystemApplication.model.Bid;
import com.example.BiddingSystemApplication.model.Product;
import com.example.BiddingSystemApplication.repository.BidRepository;
import com.example.BiddingSystemApplication.repository.ProductRepository;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Bid getBidById(Long id) {
        return bidRepository.findById(id).orElse(null);
    }

    public Bid saveBid(Bid bid) throws Exception {
    	Long productId = bid.getProduct().getId();
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent()) {
            throw new Exception("Product not found.");
        }

        Product product = optionalProduct.get();
        System.out.println(product); // Debugging to check product details

        // Check if the bid amount is less than the product's base price
        if (bid.getAmount() < product.getBasePrice()) {
            throw new InvalidBidAmountException("Bid amount cannot be less than the base price.");
        }

        // Set the fully loaded product entity back to the bid
        bid.setProduct(product);

        // Save the bid
        return bidRepository.save(bid);
    }
}
