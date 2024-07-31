package com.example.BiddingSystemApplication.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.BiddingSystemApplication.model.Bid;
import com.example.BiddingSystemApplication.model.Product;
import com.example.BiddingSystemApplication.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public Product endBiddingForProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            Optional<Bid> highestBid = productRepository.findTopByProductOrderByAmountDesc(product);

//            if (highestBid != null) {
                if (highestBid.isPresent()) { 
                	Bid highestBidForProduct = highestBid.get();
                product.setWinner(highestBidForProduct.getUser());
                productRepository.save(product);

                // Send notification to the winner
                sendNotification(highestBidForProduct.getUser().getEmail(), product.getName());
                
                return product;
            }
        }
        return product;
    }

    private void sendNotification(String email, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Congratulations! You won the bid");
        message.setText("You have won the bid for the product: " + productName);

        mailSender.send(message);
    }
}