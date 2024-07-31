package com.example.BiddingSystemApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BiddingSystemApplication.model.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
}

