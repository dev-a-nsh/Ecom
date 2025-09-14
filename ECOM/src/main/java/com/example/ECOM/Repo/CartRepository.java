package com.example.ECOM.Repo;

import com.example.ECOM.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart , String> {
}
