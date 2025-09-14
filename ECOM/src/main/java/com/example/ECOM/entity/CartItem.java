package com.example.ECOM.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private String productId;   // reference to product
    private String name;        // snapshot of product name
    private double price;       // snapshot of price at time of adding
    private int quantity;
}
