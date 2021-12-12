package com.example.rentalclub.repository;

import com.example.rentalclub.Modal.MotorBike;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MotorRepository extends MongoRepository<MotorBike, String> {
    public List<MotorBike> findAllByMakeEquals(String make);
    public List<MotorBike> findAllByEngineCapacityEquals(String engine);
}
