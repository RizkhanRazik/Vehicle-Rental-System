package com.company;


import java.io.IOException;

//Interface RentalVehicleManager that acts as the Contract to implement the following abstract methods
public interface RentalVehicleManager {
    void addVehicle(Vehicle vehicle);
    void deleteVehicle(Vehicle vehicle);
    void printListOfVehicles();
    void saveVehicle();
    void updateVehicle( Vehicle vehicle ,int index);
    boolean runMenu() throws IOException;

    //Methods along with the method Definition and parametre types
}
