package com.example.rentalclub.service;

import com.example.rentalclub.Modal.Customer;

import java.util.List;

public interface RentalClub {
    Customer add(Customer customer);
    void delete(Customer customer);
    List<Customer> viewAll();
    Customer update(Customer customer);

}
