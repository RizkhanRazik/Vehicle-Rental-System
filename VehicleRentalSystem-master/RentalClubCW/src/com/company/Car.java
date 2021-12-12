package com.company;

//a sub class Car that is extended by the Super Class Vehicle
public class Car extends Vehicle {
    private String noOfDoors;
    private boolean havehood;

    //Instance Variables Only Belonging to the Car
    //Public Constructor for Car containing the two insatnce Variables
    public Car(String noOfDoors, boolean havehood) {
        this.noOfDoors = noOfDoors;
        this.havehood = havehood;
    }

    //Public Constructor having the Super classes constructor and the two member varibales of the Class initialozed
    public Car(String make, String model, String plateNo, String contactNo, Capacity capacity, Shedule shedule, Boolean isAvailable, int transmission, String noOfDoors, boolean havehood) {
        super(make, model, plateNo, contactNo, capacity, shedule, isAvailable, transmission);
        this.noOfDoors = noOfDoors;
        this.havehood = havehood;
    }

    //Getter and setter Methods for the two instance Varibales
    public boolean isHavehood() {
        return havehood;
    }

    public void setHavehood(boolean havehood) {
        this.havehood = havehood;
    }

    public String getNoOfDoors() {
        return noOfDoors;
    }

    public void setNoOfDoors(String noOfDoors) {
        this.noOfDoors = noOfDoors;
    }



    public String getVehicle(){
        return "Car";
    }


    //To String override method for the Printing Of Vehicles
    @Override
    public String toString() {
        return "" +
                "noOfDoors='" + noOfDoors + '\'' +
                ", havehood=" + havehood +
                ' ';
    }



}
