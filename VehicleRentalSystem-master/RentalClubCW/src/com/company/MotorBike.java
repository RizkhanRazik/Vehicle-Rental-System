package com.company;

//A sub Class motor bike extending the Super Class Vehicle
public class MotorBike extends Vehicle {
    private boolean Muffler;
    private boolean noOfDoors;
    private boolean engineState;
    private boolean haveCarrier;

    //Instance Variables in the motorbike Class

    //Public Constructor for the four Instance Variables for the Motor Bike Class
    public MotorBike(boolean muffler, boolean noOfDoors, boolean engineState, boolean haveCarrier) {
        this.Muffler = muffler;
        this.noOfDoors = noOfDoors;
        this.engineState = engineState;
        this.haveCarrier = haveCarrier;
    }

    //Public Constructor for the Motorbike Class along with the Super Class Constructor and the 4 instance variables
    public MotorBike(String make, String model, String plateNo, String contactNo, Capacity capacity, Shedule shedule, Boolean isAvailable, int transmission, boolean muffler, boolean noOfDoors, boolean engineState, boolean haveCarrier) {
        super(make, model, plateNo, contactNo, capacity, shedule, isAvailable, transmission);
        Muffler = muffler;
        this.noOfDoors = noOfDoors;
        this.engineState = engineState;
        this.haveCarrier = haveCarrier;
    }


    // Getter and Setter Methods for the 4 instance Variables
    public boolean isMuffler() {
        return Muffler;
    }

    public void setMuffler(boolean muffler) {
        Muffler = muffler;
    }

    public boolean isNoOfDoors() {
        return noOfDoors;
    }

    public void setNoOfDoors(boolean noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public boolean isEngineState() {
        return engineState;
    }

    public void setEngineState(boolean engineState) {
        this.engineState = engineState;
    }

    public boolean isHaveCarrier() {
        return haveCarrier;
    }

    public void setHaveCarrier(boolean haveCarrier) {
        this.haveCarrier = haveCarrier;
    }


    @Override
    public String getVehicle() {
        return "Motorbike";
    }

    //To String Override Methods to Print the List of Vehicles
    @Override
    public String toString() {
        return " " +
                "Muffler=" + Muffler +
                ", noOfDoors=" + noOfDoors +
                ", engineState=" + engineState +
                ", haveCarrier=" + haveCarrier +
                ' ';
    }

}
