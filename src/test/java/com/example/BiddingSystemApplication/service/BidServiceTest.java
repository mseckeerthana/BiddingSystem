package com.example.BiddingSystemApplication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.BiddingSystemApplication.exception.InvalidBidAmountException;
import com.example.BiddingSystemApplication.model.Bid;
import com.example.BiddingSystemApplication.model.Product;
import com.example.BiddingSystemApplication.model.User;
import com.example.BiddingSystemApplication.repository.BidRepository;
//import com.example.BiddingSystemApplication.service.BidService;
import com.example.BiddingSystemApplication.repository.ProductRepository;

@SpringBootTest
public class BidServiceTest {
	
	@Mock
	BidRepository bidRepository;
	
	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	BidService bidService;
	
	@Test
	public void testBidValidationErrorOnCreation() {
		
		Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setBasePrice(100.0);
        product.setSlotStartTime(LocalDateTime.now().minusHours(1));
        product.setSlotEndTime(LocalDateTime.now().plusHours(1));
        
     // Create a User
        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        
        List<Bid> bids = new ArrayList<>();
        Bid bid = new Bid();
        bid.setId(1L);
        bid.setAmount(50.0);
        bid.setProduct(product);
        bid.setUser(user);
        bids.add(bid);

        product.setBids(bids);
        
        // Mock product repository to return the product
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        
        assertThrows(InvalidBidAmountException.class,()->{
        	bidService.saveBid(bid);
        });

	}
	
	@Test
	public void testBidValidationNoErrorOnCreation() throws Exception {
		
		Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setBasePrice(100.0);
        product.setSlotStartTime(LocalDateTime.now().minusHours(1));
        product.setSlotEndTime(LocalDateTime.now().plusHours(1));
        
     // Create a User
        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        
        List<Bid> bids = new ArrayList<>();
        Bid bid = new Bid();
        bid.setId(1L);
        bid.setAmount(150.0);
        bid.setProduct(product);
        bid.setUser(user);
        bids.add(bid);

        product.setBids(bids);
        
        // Mock product repository to return the product
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(bidRepository.save(bid)).thenReturn(bid);


        Bid b = bidService.saveBid(bid);
        
        assertEquals(b.getId(), bid.getId());

	}
}
