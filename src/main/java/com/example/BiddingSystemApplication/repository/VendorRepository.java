package com.example.BiddingSystemApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BiddingSystemApplication.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}

