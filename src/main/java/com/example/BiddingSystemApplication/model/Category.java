package com.example.BiddingSystemApplication.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends BaseModel {
   String name;
   
   @OneToMany(mappedBy = "category")
   List<Product> products;
}
