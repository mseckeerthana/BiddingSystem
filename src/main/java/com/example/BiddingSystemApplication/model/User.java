package com.example.BiddingSystemApplication.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    
    private String registrationNumber;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Bid> bids;

    // Getters and Setters
}
