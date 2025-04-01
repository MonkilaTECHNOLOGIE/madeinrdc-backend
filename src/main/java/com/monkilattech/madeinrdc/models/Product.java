package com.monkilattech.madeinrdc.models;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private double price;
    private String description;
    private List<String> urls;
    
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String createdAt;
}