package com.company;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

//Imports for the WestminsterRentalVehicleManager Claas and this is the Class that implements the Contract
public class WestministerRentalVehicleManager implements RentalVehicleManager {

    private MongoClient mongo = new MongoClient( "localhost" , 27017 );
    private MongoDatabase database = mongo.getDatabase("RentalClub");

    private ArrayList<Vehicle> vehicles;
    private ArrayList<String> MBPlates = new ArrayList<>();
    private ArrayList<String> plates = new ArrayList<>();
    private final int  length = 50;
    //Instance Variables for the ManagerClass along with the Arraylists and MongoDb


    // Testing Purpose
    long val;
    long val1;
    long size;



    public int Size(){
        return vehicles.size();
    }

    public long DatabaseSize(){
        return database.getCollection("Car").countDocuments() + database.getCollection("Motor Bike").countDocuments();
    }
    //


    //Public Constructor WestminsterRentalVehicle Manager
    public WestministerRentalVehicleManager() {
        vehicles = new ArrayList<Vehicle>(); //Initialing the Arraylist Vehicle
        mongo.dropDatabase("Vehicle");//Dropping Database Vehicle
        boolean collectionExistsCar = mongo.getDatabase("RentalClub").listCollectionNames()
                .into(new ArrayList<String>()).contains("Car");
        boolean collectionExistsMotorBike = mongo.getDatabase("RentalClub").listCollectionNames()
                .into(new ArrayList<String>()).contains("Motor Bike");
        boolean collectionExistsVehicle = mongo.getDatabase("RentalClub").listCollectionNames()
                .into(new ArrayList<String>()).contains("Vehicle");
        //Checking if the Collection from the Database Exists
        if(!collectionExistsVehicle){
            database.createCollection("Vehicle"); //If it Exists
        }
        if(!collectionExistsCar){
            database.createCollection("Car");
        }
        if(!collectionExistsMotorBike){
            database.createCollection("Motor Bike");
        }
    }
    // Add Vehicle Method implemented by the Rental Manager Class
    @Override
    public void addVehicle(Vehicle vehicle){ //Passing Parametres vehicle in order to be added to the Database
        val = database.getCollection("Car").countDocuments();
        val1 = database.getCollection("Motor Bike").countDocuments();
        size = val + val1;
        // Getting the Size of the Documents from the Databasae
        Scanner scanner = new Scanner(System.in);
        if (size < this.length) { //Checking if the size is less than 50
            vehicles.add(vehicle);
            System.out.println("The Vehicled Added is Saved to The Database ...");
            this.saveVehicle(); //Runnnig the automatic Save Vehicle Method to save the vehicle to the Database

        }
        else{
            System.out.println("There is no Space for the vehicle to be added");//If there is no space the error message to be printed
        }
        System.out.print("The Amount Of Parking Slots Remaining");
        System.out.println(length - DatabaseSize());

    }



    //Delete Vehicle implemented by the Rental Manager interface
    @Override
    public void deleteVehicle(Vehicle vehicle) { //Passing the vehicle as a parametre to be deleted
        if(vehicle instanceof Car){ // To Check if the particular vehicle to be deleted is a Car or a motor Bike to Display the Message Accordingly
            System.out.println("The Deleted Vehicle Was a Car");
        }
        else{
            System.out.println("The Deleted Vehicle Was a Motor Bike");
        }
        vehicles.remove(vehicle); //Then Removing the Vehicle from the Arraylist

        System.out.println("The Remaining Length of Parking Slots available :"+(length-DatabaseSize()));
         try{ //Try to handle this Code Snippet that is suspectful of throwing an error
            if(vehicle instanceof Car){ //Check instance Car or Motorbike
                MongoCollection<Document> collection = database.getCollection("Car");
                collection.deleteOne(Filters.eq("plateNo",vehicle.getPlateNo())); //If Document exists in that aprticular collection delete vehicle by the plateNo
            }
            else {
                MongoCollection<Document> collection = database.getCollection("Motor Bike");
                collection.deleteOne(Filters.eq("plateNo",vehicle.getPlateNo())); //If Document exists in that aprticular collection delete vehicle by the plateNo
            }

        }catch (Exception ex){ //Catch to handle the Exception that is thrown
            System.out.println("There is no such vehicle with this Particular Plate Number"); //If no such vehicle Exists in the Database display this error message
        }
    }

    // Print List of Vehicles Implemented by the RentalManager Interface
    @Override
    public void printListOfVehicles() {
        Collections.sort(vehicles); //Sorting the Vehicles According to make, the compatrable method implemented by the abstract class vehicle
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("                          List Of Vehicles Currently Added                                                    ");
        for(Vehicle vehicle:vehicles){
            if(vehicle instanceof Car){
                System.out.println("Car "+"   |   "+vehicle.getMake() +"   |   "+vehicle.getModel()+"   |   "+vehicle.getPlateNo()+"   |   "+vehicle.getContactNo()+"   |   "+vehicle.getAvailable()+"   |   "+vehicle.getShedule().getDay()+"/"+vehicle.getShedule().getMonth()+"/"+vehicle.getShedule().getYear());
            }
            else{
                System.out.println("Motorbike "+"   |   "+vehicle.getMake()+"   |   "+vehicle.getModel()+"   |   "+vehicle.getPlateNo()+"   |   "+vehicle.getContactNo()+"   |   "+vehicle.getAvailable()+"   |   "+vehicle.getShedule().getDay()+"/"+vehicle.getShedule().getMonth()+"/"+vehicle.getShedule().getYear());
            }

        }// Printing the List of Vehicles cuurently in the Arraylist
        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("                            List Of Vehicles Already Added                                                      ");
        //Printing the List Of Vehicles from the Database
        int i = 1;
        MongoCollection<Document> collection = database.getCollection("Car");
        FindIterable<Document> iterDoc = collection.find().sort(new BasicDBObject("make",1));// Sorting the Vehicle according to the Vehicle make

        Iterator it = iterDoc.iterator();

        while (it.hasNext()) { //Printing All the Documents from Mongodb to the Console
            System.out.println(it.next());

        }

        MongoCollection<Document> collection1 = database.getCollection("Motor Bike");
        FindIterable<Document> iterDoc1 = collection1.find().sort(new BasicDBObject("make",1)); // Sorting the Vehicle according to the Vehicle make
        Iterator it1 = iterDoc1.iterator();

        while (it1.hasNext()) {//Printing all the Documents from mongodb to the Console
            System.out.println(it1.next());
            i++;
        }


        System.out.println("-----------------------------------------------------------------------------------------------");
    }
    public static boolean activityExistsCar(MongoDatabase db, String plate) { // to check if the plate number exists in the database collection for car
        FindIterable<Document> iterable = db.getCollection("Car").find(new Document("plateNo", plate));
        return iterable.first() != null;
    }
    public static boolean activityExistsMB(MongoDatabase db, String plate) { // to check if the plate number exists in the database collection for motorbike
        FindIterable<Document> iterable = db.getCollection("Motor Bike").find(new Document("plateNo", plate));
        return iterable.first() != null;
    }




    // Save Vehicle to the Database
    @Override
    public void saveVehicle(){
        MongoCollection<Document> collection = database.getCollection("Car");
        MongoCollection<Document> collection1 = database.getCollection("Motor Bike");
        for(Vehicle vehicle : vehicles){ // Run through every instance of the Vehicle
            if(vehicle instanceof Car){ // If the instance is a car
                if(!activityExistsCar(database,vehicle.getPlateNo())){ // Check if the plate Number already exists if it exists dont add to the Database
                    Document document = new Document("type","Car").append("make",vehicle.getMake()).append("model",vehicle.getModel()).append("plateNo",vehicle.getPlateNo()).append("contactNumber",vehicle.getContactNo()).append("seatCapacity",vehicle.getCapacity().getSeatCapacity()).append("engineCapacity",vehicle.getCapacity().getEngineCapacity()).append("dateAdded",vehicle.getShedule().getDay()+"/"+vehicle.getShedule().getMonth()+"/"+vehicle.getShedule().getYear()).append("available",vehicle.getAvailable()).append("transmission",vehicle.getTransmission()).append("numberOfDoors",((Car) vehicle).getNoOfDoors()).append("havehood",((Car) vehicle).isHavehood());
                    collection.insertOne(document);
                }
            }
            else{
                if(!activityExistsMB(database,vehicle.getPlateNo())){ // Check if the plate Number already exists if it exists dont add to the Database
                    Document document = new Document("type","Motor Bike").append("make",vehicle.getMake()).append("model",vehicle.getModel()).append("plateNo",vehicle.getPlateNo()).append("contactNumber",vehicle.getContactNo()).append("seatCapacity",vehicle.getCapacity().getSeatCapacity()).append("engineCapacity",vehicle.getCapacity().getEngineCapacity()).append("dateAdded",vehicle.getShedule().getDay()+"/"+vehicle.getShedule().getMonth()+"/"+vehicle.getShedule().getYear()).append("available",vehicle.getAvailable()).append("transmission",vehicle.getTransmission()).append("haveMuffler",((MotorBike) vehicle).isMuffler()).append("engineState",((MotorBike) vehicle).isEngineState()).append("haveCarrier",((MotorBike) vehicle).isHaveCarrier());
                    collection1.insertOne(document);


                }
            }

        }
    }

    //Update the Vehicle
    @Override
    public void updateVehicle(Vehicle vehicle , int value) {
        vehicles.set(value,vehicle);
    }

    // Running the Menu
    @Override
    public boolean runMenu() throws IOException {
        boolean exit = false;


        // Print all the Options the manager can manupilate From
        System.out.println("Select Options Below ");
        System.out.println("\t"+"1) Add Vehicle ");
        System.out.println("\t"+"2) Delete Vehicle ");
        System.out.println("\t"+"3) Print List Of Vehicles ");
        System.out.println("\t"+"4) Save Vehicles ");
        System.out.println("\t"+"5) Update Vehicle ");
        System.out.println("\t"+"6) Open GUI  ");
        System.out.println("\t"+"7) Exit ");

        // Define and initialize all the Scanners (int,string,boolean)
        Scanner scanner = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner bool = new Scanner(System.in);

        // Initialize the variables
        int value = 0;
        boolean except = false;
        boolean ex = false;
        //Validate Inputs
        do {
            if(scanner.hasNextInt()){
                value = scanner.nextInt();
                except = true;
            }else{
                scanner.nextLine();
                System.out.println("Enter a valid Integer value from the Console");
            }
        }while (!except);
        if(value==1){
            System.out.println("Choose vehicle..");
            System.out.println("\t"+"1) Car");
            System.out.println("\t"+"2) MotorBike");
            int choice = 0;
            do {
                if(scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    ex = true;
                }else{
                    scanner.nextLine();
                    System.out.println("Enter a valid Integer value from the Console");
                }

            }while (!ex);
            if(choice==1){ //If the Choice == 1 then add vehicle Car
                System.out.println("Are Cars available(true/false) : ");
                Boolean available = false;
                boolean av = false;
                do {
                    if(bool.hasNextBoolean()){
                        available = bool.nextBoolean();
                        av = true;
                    }
                    else{
                        bool.nextLine();
                        System.out.println("Enter a valid Response(true/false)");
                    }
                }while (!av);
                if(available){ //Check if vehicle is available
                    boolean check = false;
                    boolean ava = false;
                    boolean rep = false;
                    // Enter all the Information for it to be added to the Vehicle list
                    System.out.println("Enter Car Make : ");
                    String Make = input.nextLine();
                    System.out.println("Enter Car model : ");
                    String model = input.nextLine();
                    System.out.println("Enter Car plateNo : ");
                    String plateNo = "";
                    while(!rep){
                        plateNo = input.nextLine();
                        if(activityExistsCar(database,plateNo) || activityExistsMB(database,plateNo)){
                            System.out.println("Plate Number Already Exists !!");
                            rep = false;
                            System.out.println("Re-Enter Plate Number ");
                        }
                        else{
                            rep = true;
                            plates.add(plateNo);
                        }
                    }
                    System.out.println("Enter Car Contact No : ");
                    String contactNo = input.nextLine();
                    System.out.println("Enter Car seatCapacity : ");
                    String seatCapacity = input.nextLine();
                    System.out.println("Enter Car engineCapacity : ");
                    String engineCapacity = input.nextLine();
                    System.out.println("Enter Car Added Date : ");
                    String pickupdate = input.nextLine();
                    String[] dates = pickupdate.split("/");
                    System.out.println("Enter Car transmission : ");
                    int transmission = 0;
                    //Validate Inputs
                    do{
                        if(scanner.hasNextInt()){
                            transmission = scanner.nextInt();
                            check = true;
                        }
                        else{
                            scanner.nextLine();
                            System.out.println("Enter Valid Car transmission ");
                        }
                    }while (!check);
                    System.out.println("Enter Car No Of Doors : ");
                    String noOfDoors = input.nextLine();
                    System.out.println("Does Car havehood : ");
                    boolean hood = false;
                    do{
                        if(bool.hasNextBoolean()){
                            hood = bool.nextBoolean();
                            ava = true;
                        }
                        else{
                            bool.nextLine();
                            System.out.println("Enter either true/false ");
                        }
                    }while (!ava);
                    Vehicle vehicle = new Car(Make,model,plateNo,contactNo,new Capacity(seatCapacity,engineCapacity),new Shedule(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2])),available,transmission,noOfDoors,hood);
                    this.addVehicle(vehicle);
                }

                else{
                    System.out.println("Car is not available ! ");
                }

            }
            else if(choice==2){// Add a vehicle Motor bike
                boolean av = false;
                System.out.println("Is Motorbike available ");
                Boolean available = false;
                //validate inputs
                do {
                    if(bool.hasNextBoolean()){
                        available = bool.nextBoolean();
                        av = true;
                    }
                    else{
                        bool.nextLine();
                        System.out.println("Enter a valid Response(true/false)");
                    }
                }while (!av);
                if(available){ //Check if it is available
                    boolean check = false;
                    boolean ava = false;
                    boolean states = false;
                    boolean carriers = false;
                    boolean haveDoors = false;
                    boolean rep = false;
                    System.out.println("Enter Motorbike Make : ");
                    String Make = input.nextLine();
                    System.out.println("Enter Motorbike model : ");
                    String model = input.nextLine();
                    System.out.println("Enter Motorbike plateNo : ");
                    String plateNo = "";
                    while(!rep){ //Check if plate Number already exits
                        plateNo = input.nextLine();
                        if(activityExistsMB(database,plateNo) || activityExistsCar(database,plateNo)){
                            System.out.println("Plate Number Already Exists !!");
                            rep = false;
                            System.out.println("Re-Enter Plate Number ");
                        }
                        else{
                            rep = true;
                            MBPlates.add(plateNo);
                        }
                    }
                    System.out.println("Enter Motorbike Contact No : ");
                    String contactNo = input.nextLine();
                    System.out.println("Enter Motorbike seatCapacity : ");
                    String seatCapacity = input.nextLine();
                    System.out.println("Enter Motorbike engineCapacity : ");
                    String engineCapacity = input.nextLine();
                    System.out.println("Enter Motorbike Added Date : ");
                    String pickupdate = input.nextLine();
                    String[] dates = pickupdate.split("/");
                    System.out.println("Enter Motorbike transmission : ");
                    int transmission = 0;
                    do{
                        if(scanner.hasNextInt()){
                            transmission = scanner.nextInt();
                            check = true;
                        }
                        else{
                            scanner.nextLine();
                            System.out.println("Enter Valid Car transmission ");
                        }
                    }while (!check);
                    System.out.println("Does Motor Bike have Muffler : ");
                    boolean muffler = false;
                    do{
                        if(bool.hasNextBoolean()){
                            muffler = bool.nextBoolean();
                            ava = true;
                        }
                        else{
                            bool.nextLine();
                            System.out.println("Enter either true/false ");
                        }
                    }while (!ava);
                    System.out.println("Does Motor Bike have doors : ");
                    boolean door = false;
                    do{
                        if(bool.hasNextBoolean()){
                            door = bool.nextBoolean();
                            haveDoors = true;
                        }
                        else{
                            bool.nextLine();
                            System.out.println("Enter either true/false ");
                        }
                    }while (!haveDoors);
                    System.out.println("Is the Motorbike having a good engine State : ");
                    boolean state = false;
                    do{
                        if(bool.hasNextBoolean()){
                            state = bool.nextBoolean();
                            states = true;
                        }
                        else{
                            bool.nextLine();
                            System.out.println("Enter either true/false ");
                        }
                    }while (!states);
                    System.out.println("Does Motor Bike have Carrier : ");
                    boolean carrier = false;
                    do{
                        if(bool.hasNextBoolean()){
                            carrier = bool.nextBoolean();
                            carriers = true;
                        }
                        else{
                            bool.nextLine();
                            System.out.println("Enter either true/false ");
                        }
                    }while (!carriers);
                    Vehicle vehicle = new MotorBike(Make,model,plateNo,contactNo,new Capacity(seatCapacity,engineCapacity),new Shedule(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2])),available,transmission,muffler,door,state,carrier);
                    this.addVehicle(vehicle);
                }
                else{
                    System.out.println("Motor bike not available ");
                }

            }
            else{
                System.out.println("Enter the Correct No :");
            }
        }
        else if(value==2){ // Delete a vehicle
            MongoCollection<Document> collection = database.getCollection("Car");
            FindIterable<Document> documents = collection.find().projection(Projections.fields(Projections.include("plateNo")));
            Iterator iterator = documents.iterator();
            while (iterator.hasNext()){
                Object value1 =  iterator.next();
                int an = value1.toString().indexOf("}");
                int ac = value1.toString().indexOf("plateNo")+8;
                plates.add(value1.toString().substring(ac,an));

            }
            MongoCollection<Document> collection1 = database.getCollection("Motor Bike");
            FindIterable<Document> documents1 = collection1.find().projection(Projections.fields(Projections.include("plateNo")));
            Iterator iterator1 = documents1.iterator();
            while (iterator1.hasNext()){
                Object value1 =  iterator1.next();
                int an = value1.toString().indexOf("}");
                int ac = value1.toString().indexOf("plateNo")+8;
                MBPlates.add(value1.toString().substring(ac,an));

            }
            LinkedHashSet<String> hashSet = new LinkedHashSet<>(plates);
            ArrayList<String> platesNew = new ArrayList<>(hashSet);

            LinkedHashSet<String> hashSet1 = new LinkedHashSet<>(MBPlates);
            ArrayList<String> Motor = new ArrayList<>(hashSet1);

            System.out.println("----------------------------------------------------------------------");
            System.out.println(" Choose plateNo to be Deleted ");
            for(String plate : platesNew){
                System.out.println("Car "+plate+" ");
            }
            for(String plates1 : Motor){
                System.out.println("Motor Bike "+ plates1+" ");
            }
            System.out.println("-----------------------------------------------------------------------");
            System.out.println();
            int c = 0;
            System.out.println("Enter plateNo to be Deleted ");
            String plateNo = input.nextLine();

            boolean flag = false;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getPlateNo().equals(plateNo)) {
                    this.deleteVehicle(vehicle);
                    if(vehicle instanceof Car){
                        plates.remove(plateNo);
                    }
                    else{
                        MBPlates.remove(plateNo);
                    }
                    flag = true;
                    break;
                } else {
                    continue;
                }
            }
            boolean val = false;
            if(!flag){
                if(activityExistsCar(database,plateNo)){
                    val = true;
                    collection.deleteOne(Filters.eq("plateNo",plateNo));
                    System.out.println("The Deleted Vehicle Was a Car");
                    plates.remove(plateNo);
                }
                else{
                    if(activityExistsMB(database,plateNo)){
                        val = true;
                        collection1.deleteOne(Filters.eq("plateNo",plateNo));
                        System.out.println("The Deleted Vehicle Was a Motor Bike");
                        MBPlates.remove(plateNo);
                    }
                }
            if(val){
                System.out.println("The Remaining Length of Parking Slots available :"+(length-database.getCollection("Car").countDocuments()-database.getCollection("Motor Bike").countDocuments()));
            }
            else{
                System.out.println("There is no such vehicle with this Particular Plate Number");
            }

            }
            plates.clear();
            MBPlates.clear();
        }
        else if(value==3){ // Print list Of vehicle
            this.printListOfVehicles();
        }
        else if(value==4){ // Save Vehicle
            this.saveVehicle();
        }
        else if(value==5){ // Update Vehicle
            System.out.println("  Choose Vehicle to be Updated ");
            this.printListOfVehicles();
            System.out.println("Choose What Part of The Vehicle to be Updated...");
            System.out.println("\t"+"1) Customer Contact Number :");
            System.out.println("\t"+"2) Vehicle Added Date :");
            System.out.println("\t"+"3) Vehicle Is available :");
            System.out.println("\t"+"4) Exit :");
            int out = 0;
            boolean check = false;
            do {
                if(scanner.hasNextInt()){
                    out = scanner.nextInt();
                    check = true;
                }
                else{
                    scanner.nextLine();
                    System.out.println("Enter a Valid Integer from the Console Menu !");
                }
            }while (!check);
            while(out !=4){
                    if(out==1){
                        boolean val = false;
                        MongoCollection<Document> collection = database.getCollection("Car");
                        MongoCollection<Document> collection1 = database.getCollection("Motor Bike");
                        System.out.println("Enter Plate Number : ");
                        String plateNo = input.nextLine();
                        System.out.println("Enter New contactNumber : ");
                        String contactNo = input.nextLine();
                        for (Vehicle vehicle:vehicles){
                            if(!vehicle.getContactNo().equals(contactNo) && vehicle.getPlateNo().equals(plateNo)){
                                int index = vehicles.indexOf(vehicle);
                                vehicle.setContactNo(contactNo);
                                this.updateVehicle(vehicle,index);
                                val = true;
                                break;
                            }
                        }
                        if(activityExistsCar(database,plateNo)){
                            Bson filter = new Document("plateNo",plateNo);
                            Bson newValue = new Document("contactNumber",contactNo);
                            Bson update = new Document("$set",newValue);
                            collection.updateOne(filter,update);
                            val = true;
                        }
                        else{
                            if(activityExistsMB(database,plateNo)){
                                Bson filter = new Document("plateNo",plateNo);
                                Bson newValue = new Document("contactNumber",contactNo);
                                Bson update = new Document("$set",newValue);
                                collection1.updateOne(filter,update);
                                val = true;
                            }
                        }
                        if(!val){
                            System.out.println("The Plate Number doesn't exist in the Garage");
                        }

                    }
                    else if(out==2){
                        boolean val = false;
                        MongoCollection<Document> collection = database.getCollection("Car");
                        MongoCollection<Document> collection1 = database.getCollection("Motor Bike");
                        System.out.println("Enter Car plateNo : ");
                        String plateNo = input.nextLine();
                        System.out.println("Enter New Adding Date : ");
                        String date = input.nextLine();
                        String[] dates = date.split("/");
                        for(Vehicle vehicle:vehicles){
                            if((vehicle.getShedule().getDay()!=Integer.parseInt(dates[0]) && vehicle.getShedule().getMonth()!=Integer.parseInt(dates[1]) && vehicle.getShedule().getYear()!=Integer.parseInt(dates[2])) && vehicle.getPlateNo().equals(plateNo)){
                                int index = vehicles.indexOf(vehicle);
                                vehicle.setShedule(new Shedule(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2])));
                                this.updateVehicle(vehicle,index);
                                val = true;
                                break;
                            }
                        }
                        if(activityExistsCar(database,plateNo)){
                            Bson filter = new Document("plateNo",plateNo);
                            Bson newValue = new Document("dateAdded",Integer.parseInt(dates[0])+"/"+Integer.parseInt(dates[1])+"/"+Integer.parseInt(dates[2]));
                            Bson update = new Document("$set",newValue);
                            collection.updateOne(filter,update);
                            val = true;
                        }
                        else{
                            if(activityExistsMB(database,plateNo)){
                                Bson filter = new Document("plateNo",plateNo);
                                Bson newValue = new Document("dateAdded",Integer.parseInt(dates[0])+"/"+Integer.parseInt(dates[1])+"/"+Integer.parseInt(dates[2]));
                                Bson update = new Document("$set",newValue);
                                collection1.updateOne(filter,update);
                                val = true;
                            }
                        }
                        if(!val){
                            System.out.println("Plate Number doesn't exist in the Garage");
                        }
                    }
                    else if(out==3){
                        boolean val = false;
                        MongoCollection<Document> collection = database.getCollection("Car");
                        MongoCollection<Document> collection1 = database.getCollection("Motor Bike");

                        System.out.println("Enter Car plateNo :");
                        String plateNo = input.nextLine();
                        System.out.println("Is Vehicle available : ");
                        boolean available = bool.nextBoolean();
                        for(Vehicle vehicle : vehicles){
                            if(vehicle.getPlateNo().equals(plateNo) && vehicle.getAvailable() != available){
                                int index = vehicles.indexOf(vehicle);
                                vehicle.setAvailable(available);
                                this.updateVehicle(vehicle,index);
                                val = true;
                                break;
                            }
                        }
                        if(activityExistsCar(database,plateNo)){
                            Bson filter = new Document("plateNo",plateNo);
                            Bson newValue = new Document("available",available);
                            Bson update = new Document("$set",newValue);
                            collection.updateOne(filter,update);
                            val = true;
                        }
                        else{
                            if(activityExistsMB(database,plateNo)){
                                Bson filter = new Document("plateNo",plateNo);
                                Bson newValue = new Document("available",available);
                                Bson update = new Document("$set",newValue);
                                collection1.updateOne(filter,update);
                                val = true;
                            }
                        }
                        if(!val){
                            System.out.println("Plate Number doesn't exist in the Garage");
                        }
                    }
                    else{
                        System.out.println("Enter a Number matching the Console menu !");
                    }
                    System.out.println("\t"+"1) Customer contactNumber :");
                    System.out.println("\t"+"2) Vehicle Added Date :");
                    System.out.println("\t"+"3) Vehicle Is available :");
                    System.out.println("\t"+"4) Exit :");
                    out = scanner.nextInt();
            }

        }
        else if(value==6){ // Run GUI
            Desktop d = Desktop.getDesktop();
            try {
                d.browse(new URI("http://localhost:4200"));
            } catch (IOException | URISyntaxException e2) {
                e2.printStackTrace();
            }
        }
        else if(value==7){ // Exit
            this.saveVehicle();
            exit = true;
        }
        else{ // Wrong number entered try again
            System.out.println("Wrong Number Entered");
            System.out.println("Try Again");
        }

        return exit;
    }
    // Testing Purpose
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    //

}
