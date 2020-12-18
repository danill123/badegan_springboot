package com.example.demo.repository;

import com.example.demo.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface UserRepository extends MongoRepository<Users, String> {
    @Query(value="{ email : ?0}", fields="{ email : 1 ,name: 1, lastname: 1, password: 1}")
    Users findByEmail(String email);

    @Query(value="{ email : ?0}", fields="{ email : 1 ,name: 1, lastname: 1, password: 1}")
    List<Users> DoesEmailExist(String email);
}
