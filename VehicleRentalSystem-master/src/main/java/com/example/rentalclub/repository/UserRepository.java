package com.example.rentalclub.repository;

import com.example.rentalclub.Modal.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Customer,String> {

    public Customer findByDropOff(String dropOff);

}
