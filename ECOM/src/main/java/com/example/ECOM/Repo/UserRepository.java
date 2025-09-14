package com.example.ECOM.Repo;

import com.example.ECOM.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User , String>{
    User findByUsername(String username);

}
