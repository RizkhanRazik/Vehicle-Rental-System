package com.example.rentalclub.Modal;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Motor Bike")
public class MotorBike{
    private String type;
    private String make;
    private String model;
    private String plateNo;
    private int transmission;
    private String engineCapacity;

    public MotorBike(String type, String make, String model, String plateNo, int transmission, String engineCapacity) {
        this.type = type;
        this.make = make;
        this.model = model;
        this.plateNo = plateNo;
        this.transmission = transmission;
        this.engineCapacity = engineCapacity;
    }

    public int getTransmission() {
        return transmission;
    }

    public void setTransmission(int transmission) {
        this.transmission = transmission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}
