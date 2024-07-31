package com.example.BiddingSystemApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BiddingSystemApplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

