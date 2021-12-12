package com.company;

import java.util.Objects;

//The Abstract Class Vehicle and this and has a Comparablr <Vehicle> to print the Vehicles according to Ascending Order
public abstract class Vehicle implements Comparable<Vehicle> {
    private String Make;
    private String Model;
    private String PlateNo;
    private String ContactNo;
    private Capacity capacity;
    private Shedule shedule;
    private Boolean isAvailable;
    private int transmission;

    //Instance Member Variables for the Vehicle Class

    //Public Constructor For Vehicle with No parametres inside
    public Vehicle(){}

    // Public Class Constructor Vehicle with all the member variables initialized
    public Vehicle(String make, String model, String plateNo, String contactNo, Capacity capacity, Shedule shedule, Boolean isAvailable, int transmission) {
        Make = make;
        Model = model;
        PlateNo = plateNo;
        setContactNo(contactNo);
        this.capacity = capacity;
        setShedule(shedule);
        setAvailable(isAvailable);
        this.transmission = transmission;
    }

    //Getter and Setter Methods for the Instance Variables

    public int getTransmission() {
        return transmission;
    }

    public void setTransmission(int transmission) {
        this.transmission = transmission;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getPlateNo() {
        return PlateNo;
    }

    public void setPlateNo(String plateNo) {
        PlateNo = plateNo;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public Shedule getShedule() {
        return shedule;
    }

    public void setShedule(Shedule shedule) {
        this.shedule = shedule;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public abstract String getVehicle();

    //To string method to print the Vehicles
    @Override
    public String toString() {
        return " " +
                "Make='" + Make + '\'' +
                ", Model='" + Model + '\'' +
                ", PlateNo='" + PlateNo + '\'' +
                ", ContactNo='" + ContactNo + '\'' +
                ", capacity=" + capacity +
                ", shedule=" + shedule +
                ", isAvailable=" + isAvailable +
                ", transmission=" + transmission +
                ' ';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return transmission == vehicle.transmission &&
                Objects.equals(Make, vehicle.Make) &&
                Objects.equals(Model, vehicle.Model) &&
                Objects.equals(PlateNo, vehicle.PlateNo) &&
                Objects.equals(ContactNo, vehicle.ContactNo) &&
                Objects.equals(capacity, vehicle.capacity) &&
                Objects.equals(shedule, vehicle.shedule) &&
                Objects.equals(isAvailable, vehicle.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Make, Model, PlateNo, ContactNo, capacity, shedule, isAvailable, transmission);
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.Make.compareTo(o.Make);
    }





}
