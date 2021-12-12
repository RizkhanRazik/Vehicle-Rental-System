package com.example.rentalclub.controller;

import com.example.rentalclub.Modal.Car;
import com.example.rentalclub.Modal.Customer;
import com.example.rentalclub.Modal.MotorBike;
import com.example.rentalclub.repository.CarRepository;
import com.example.rentalclub.repository.MotorRepository;
import com.example.rentalclub.repository.UserRepository;
import com.example.rentalclub.service.Email;
import com.example.rentalclub.service.EmailUtil;
import com.example.rentalclub.service.RentalClubManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vehicles")
@RestController
public class CustomerController {

    private EmailUtil emailUtil;
    private RentalClubManager rentalClubManager;
    private UserRepository userRepository;
    private CarRepository carRepository;
    private MotorRepository motorRepository;


    public CustomerController(EmailUtil emailUtil, RentalClubManager rentalClubManager, UserRepository userRepository, CarRepository carRepository, MotorRepository motorRepository) {
        this.rentalClubManager = rentalClubManager;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.motorRepository = motorRepository;
        this.emailUtil = emailUtil;

    }


    @GetMapping
    public List<Customer> get(){
//        List<Customer> customers = this.userRepository.findAll();
//        return customers;
        return this.rentalClubManager.viewAll();
    }

    @GetMapping("/car")
    public List<Car> getCar(){
//        List<Car> cars = this.carRepository.findAll();
//        return cars;
        return this.rentalClubManager.viewAllCars();
    }


    @GetMapping("/motor")
    public List<MotorBike> getMotor(){
//        List<MotorBike> motorBikes = this.motorRepository.findAll();
//        return motorBikes;
        return this.rentalClubManager.viewAllMotor();
    }




    @GetMapping("/car/make/{make}")
    public List<Car> getCarMakes(@PathVariable String make){
        return this.carRepository.findAllByMakeEquals(make);
    }

    @GetMapping("/car/engine/{engine}")
    public List<Car> getCarEngine(@PathVariable String engine){
        return this.carRepository.findAllByEngineCapacityEquals(engine);
    }


    @GetMapping("/motor/make/{make}")
    public List<MotorBike> getMotormakes(@PathVariable String make){
        return this.motorRepository.findAllByMakeEquals(make);
    }

    @GetMapping("/motor/engine/{engine}")
    public List<MotorBike> getMotorEngine(@PathVariable String engine){
        return this.motorRepository.findAllByEngineCapacityEquals(engine);
    }



    @PostMapping(value = "/customer" )
    public ResponseEntity<Customer> insert(@RequestBody Customer customer)  {
        Customer customer1 = this.userRepository.save(customer);

        return new ResponseEntity<Customer>(customer1, HttpStatus.OK);

    }


    @GetMapping("/customer/id/{id}")
    public Optional<Customer> getCustomer(@PathVariable String id){


        return this.userRepository.findById(id);
    }


    @GetMapping("/customer")
    public List<Customer> findAllDate(){
        return this.userRepository.findAll();
    }

    @DeleteMapping("/customer/id/{id}")
    public void delete(@PathVariable String id){

        this.userRepository.deleteById(id);

    }

    @PutMapping("/customer/id/{id}")
    public ResponseEntity<Customer> update(@PathVariable String id, @RequestBody Customer customer){
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
        }
        Customer customer1 = this.userRepository.save(customer);

        return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
    }

    @PostMapping("/customer/email")
    public void SendEmail(@RequestBody String email){
        emailUtil.SendEmail(email,"Confirmed Booking","Dear Customer \n Thank You For Booking a Vehicle from Rental Club \n Please Visit Us Next time \n \n Thank You \n  Aarthif Nawaz");
    }







}
