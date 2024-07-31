package com.example.BiddingSystemApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BiddingSystemApplication.model.Bid;
import com.example.BiddingSystemApplication.service.BidService;

@RestController 
@RequestMapping("/bids")
public class BidController {
    @Autowired
    private BidService bidService;

    @GetMapping
    public List<Bid> getAllBids() {
        return bidService.getAllBids();
    }

    @GetMapping("/{id}")
    public Bid getBidById(@PathVariable Long id) {
        return bidService.getBidById(id);
    }

    @PostMapping
    public Bid createBid(@RequestBody Bid bid) throws Exception {
        return bidService.saveBid(bid);
    }
}
