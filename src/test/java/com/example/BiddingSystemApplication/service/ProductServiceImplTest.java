package com.example.BiddingSystemApplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.example.BiddingSystemApplication.model.Bid;
import com.example.BiddingSystemApplication.model.Product;
import com.example.BiddingSystemApplication.model.User;
import com.example.BiddingSystemApplication.repository.BidRepository;
import com.example.BiddingSystemApplication.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BidRepository bidRepository;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification() {
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
        bid.setAmount(120.0);
        bid.setProduct(product);
        bid.setUser(user);
        bids.add(bid);

        product.setBids(bids);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        productService.endBiddingForProduct(1L);

        // Verify that mailSender.send() was called
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testEndBiddingForProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setBasePrice(100.0);
        product.setSlotStartTime(LocalDateTime.now().minusHours(1));
        product.setSlotEndTime(LocalDateTime.now().plusHours(1));
        
        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        List<Bid> bids = new ArrayList<>();
        Bid bid = new Bid();
        bid.setId(1L);
        bid.setAmount(120.0);
        bid.setProduct(product);
        bid.setUser(user);
        bids.add(bid);
        
        product.setBids(bids);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
       // when(bidRepository.findTopByProductOrderByAmountDesc(any(Product.class))).thenReturn(bid);
        //when(bidRepository.findTopByProductOrderByAmountDesc(any(Product.class))).thenReturn(bid);

        Product p = productService.endBiddingForProduct(1L);
        
        assertEquals(p.getWinner(), bid.getUser());

        // Verify that bidRepository.findTopByProductOrderByAmountDesc() was called
        //verify(bidRepository, times(1)).findTopByProductOrderByAmountDesc(any(Product.class));
    }
}
