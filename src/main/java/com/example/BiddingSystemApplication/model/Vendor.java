package com.example.BiddingSystemApplication.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vendor extends BaseModel{

    private String name;

    @OneToMany(mappedBy = "vendor")
    private List<Product> products;

    // Getters and Setters
}
