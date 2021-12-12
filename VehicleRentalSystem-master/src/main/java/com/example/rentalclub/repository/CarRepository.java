package com.example.rentalclub.repository;

import com.example.rentalclub.Modal.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car,String> {

    public List<Car> findAllByMakeEquals(String make);

    public List<Car> findAllByEngineCapacityEquals(String engine);
}
