package com.company;

// a composition class creatd for the Vehicle Class
public class Capacity {
    private String seatCapacity;
    private String engineCapacity;
    // Instance member variables for Capacity Class

    // Capaciy Constructor initilaizing the Construcore
    public Capacity(String seatCapacity, String engineCapacity) {
        this.seatCapacity = seatCapacity;
        this.engineCapacity = engineCapacity;
    }
    //Getter an setter methods for the two instance Variables
    public String getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(String seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}
