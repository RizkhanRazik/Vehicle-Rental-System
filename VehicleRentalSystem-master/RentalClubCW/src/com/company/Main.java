package com.company;



//Imports
import java.io.IOException;

//Main Class
public class Main {

    //the main method run to execute the Console Menu
    public static void main(String[] args) throws IOException {


        //A inro Design
        System.out.println("\n" +
                " __       __            __                                                      __                      _______                         __                __         ______   __            __              __ \n" +
                "/  |  _  /  |          /  |                                                    /  |                    /       \\                       /  |              /  |       /      \\ /  |          /  |            /  |\n" +
                "$$ | / \\ $$ |  ______  $$ |  _______   ______   _____  ____    ______         _$$ |_     ______        $$$$$$$  |  ______   _______   _$$ |_     ______  $$ |      /$$$$$$  |$$ | __    __ $$ |____        $$ |\n" +
                "$$ |/$  \\$$ | /      \\ $$ | /       | /      \\ /     \\/    \\  /      \\       / $$   |   /      \\       $$ |__$$ | /      \\ /       \\ / $$   |   /      \\ $$ |      $$ |  $$/ $$ |/  |  /  |$$      \\       $$ |\n" +
                "$$ /$$$  $$ |/$$$$$$  |$$ |/$$$$$$$/ /$$$$$$  |$$$$$$ $$$$  |/$$$$$$  |      $$$$$$/   /$$$$$$  |      $$    $$< /$$$$$$  |$$$$$$$  |$$$$$$/    $$$$$$  |$$ |      $$ |      $$ |$$ |  $$ |$$$$$$$  |      $$ |\n" +
                "$$ $$/$$ $$ |$$    $$ |$$ |$$ |      $$ |  $$ |$$ | $$ | $$ |$$    $$ |        $$ | __ $$ |  $$ |      $$$$$$$  |$$    $$ |$$ |  $$ |  $$ | __  /    $$ |$$ |      $$ |   __ $$ |$$ |  $$ |$$ |  $$ |      $$/ \n" +
                "$$$$/  $$$$ |$$$$$$$$/ $$ |$$ \\_____ $$ \\__$$ |$$ | $$ | $$ |$$$$$$$$/         $$ |/  |$$ \\__$$ |      $$ |  $$ |$$$$$$$$/ $$ |  $$ |  $$ |/  |/$$$$$$$ |$$ |      $$ \\__/  |$$ |$$ \\__$$ |$$ |__$$ |       __ \n" +
                "$$$/    $$$ |$$       |$$ |$$       |$$    $$/ $$ | $$ | $$ |$$       |        $$  $$/ $$    $$/       $$ |  $$ |$$       |$$ |  $$ |  $$  $$/ $$    $$ |$$ |      $$    $$/ $$ |$$    $$/ $$    $$/       /  |\n" +
                "$$/      $$/  $$$$$$$/ $$/  $$$$$$$/  $$$$$$/  $$/  $$/  $$/  $$$$$$$/          $$$$/   $$$$$$/        $$/   $$/  $$$$$$$/ $$/   $$/    $$$$/   $$$$$$$/ $$/        $$$$$$/  $$/  $$$$$$/  $$$$$$$/        $$/ \n" +
                "                                                                                                                                                                                                               \n" +
                "                                                                                                                                                                                                               \n" +
                "                                                                                                                                                                                                               \n");
        // Using the Interface a reference to the Class that implemented Interface
        RentalVehicleManager rentalVehicleManager = new WestministerRentalVehicleManager();
        boolean start = false;
        while(!start){
            start = rentalVehicleManager.runMenu();//Run the menu till the loop becomes false
        }





    }
}
