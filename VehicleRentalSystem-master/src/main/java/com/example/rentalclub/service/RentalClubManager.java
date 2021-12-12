package com.example.rentalclub.service;

import com.example.rentalclub.Modal.Car;
import com.example.rentalclub.Modal.Customer;
import com.example.rentalclub.Modal.MotorBike;
import com.example.rentalclub.repository.CarRepository;
import com.example.rentalclub.repository.MotorRepository;
import com.example.rentalclub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RentalClubManager implements RentalClub {




    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Customer add(Customer customer) {
        return userRepository.insert(customer);
    }

    @Override
    public void delete(Customer customer) {
        userRepository.delete(customer);
    }

    @Override
    public List<Customer> viewAll() {
        return userRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        return userRepository.save(customer);
    }



    public List<Car>  viewAllCars(){
        return carRepository.findAll();
    }

    public List<MotorBike> viewAllMotor(){
        return motorRepository.findAll();
    }


}
