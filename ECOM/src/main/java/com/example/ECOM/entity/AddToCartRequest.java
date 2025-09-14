package com.example.ECOM.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class AddToCartRequest {
    private String userId;
    private String productId;
    private int quantity;
}
