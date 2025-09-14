package com.example.ECOM.Repo;

import com.example.ECOM.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository <Product , String> {

    List<Product> findByCategory(String category);
}
