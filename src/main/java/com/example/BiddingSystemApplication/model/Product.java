package com.example.BiddingSystemApplication.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseModel{
    
    private String name;
    private Double basePrice;
    private LocalDateTime slotStartTime;
    @Override
	public String toString() {
		return "Product [name=" + name + ", basePrice=" + basePrice + ", slotStartTime=" + slotStartTime
				+ ", slotEndTime=" + slotEndTime + ", category=" + category + ", vendor=" + vendor + ", winner="
				+ winner + ", bids=" + bids + "]";
	}

	private LocalDateTime slotEndTime;
    
    @ManyToOne
    @JoinColumn(name =  "category_id") 
    private Category category;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private User winner;

    @OneToMany(mappedBy = "product")
    private List<Bid> bids;

    // Getters and Setters
}
