package com.company;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;


import static org.junit.Assert.*;

public class WestministerRentalVehicleManagerTest {
    private RentalVehicleManager rentalVehicleManager;
    WestministerRentalVehicleManager westministerRentalVehicleManager = new WestministerRentalVehicleManager();
    MongoClient mongo = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongo.getDatabase("RentalClub");
    MongoCollection<Document> collection = database.getCollection("Car");

    MongoCollection<Document> collection1 = database.getCollection("Motor Bike");
    int length = 50;


    public WestministerRentalVehicleManagerTest() {
        this.rentalVehicleManager = new WestministerRentalVehicleManager();
    }

    @Before
    public void setUp() throws Exception {
        Car vehicle = new Car("Honda","Civic","CAC 1234","0773753001",new Capacity("4","1000"),new Shedule(12,8,2001),true,100,"4",true);
        MotorBike mb = new MotorBike("TVS","Jupiter","1243","07734",new Capacity("4","1000"),new Shedule(12,3,4),true,1,true,true,true,true);
        westministerRentalVehicleManager.addVehicle(vehicle);
        westministerRentalVehicleManager.addVehicle(mb);


        if(!activityExistsCar(database,vehicle.getPlateNo())){
            Document document = new Document("type","Car").append("make",vehicle.getMake()).append("model",vehicle.getModel()).append("plateNo",vehicle.getPlateNo()).append("contactNumber",vehicle.getContactNo()).append("seatCapacity",vehicle.getCapacity().getSeatCapacity()).append("engineCapacity",vehicle.getCapacity().getEngineCapacity()).append("dateAdded",vehicle.getShedule().getDay()+"/"+vehicle.getShedule().getMonth()+"/"+vehicle.getShedule().getYear()).append("available",vehicle.getAvailable()).append("transmission",vehicle.getTransmission()).append("numberOfDoors", vehicle.getNoOfDoors()).append("havehood", vehicle.isHavehood());
            collection.insertOne(document);
        }
        if(!activityExistsMB(database,mb.getPlateNo())){
            Document document = new Document("type","Motor Bike").append("make",mb.getMake()).append("model",mb.getModel()).append("plateNo",mb.getPlateNo()).append("contactNumber",mb.getContactNo()).append("seatCapacity",mb.getCapacity().getSeatCapacity()).append("engineCapacity",mb.getCapacity().getEngineCapacity()).append("dateAdded",mb.getShedule().getDay()+"/"+mb.getShedule().getMonth()+"/"+mb.getShedule().getYear()).append("available",mb.getAvailable()).append("transmission",mb.getTransmission()).append("haveMuffler", mb.isMuffler()).append("engineState", mb.isEngineState()).append("haveCarrier", mb.isHaveCarrier());
            collection1.insertOne(document);
        }

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSize(){
        System.out.println("Running Test Check Vehicle Size Before Vehicle is Added ...");
        assertEquals("Checking Vehicle Size",2,westministerRentalVehicleManager.Size());
    }
    @Test
    public void checkCapacity(){
        System.out.println("Running Test Check the Amount Of Space Available Before Adding the Vehicle ...");
        assertEquals("Checking Length Requirements",true,westministerRentalVehicleManager.Size()<length);
        assertEquals("Checking Length Requirements",false,westministerRentalVehicleManager.Size()>length);
    }

    @Test
    public void addVehicle() {

        System.out.println("Running Test Add Vehicle ...");
        Vehicle mb = new MotorBike("TVS","Jupiter","124311","07734",new Capacity("4","1000"),new Shedule(12,3,4),true,1,true,true,true,true);
        westministerRentalVehicleManager.addVehicle(mb);
        assertEquals("Adding one Vehicle to the List",3,westministerRentalVehicleManager.Size());

    }


    @Test
    public void deleteVehicle() {
        System.out.println("Running Test Delete Vehicle ...");
        Vehicle mb = new MotorBike("TVS","Jupiter","1243","07734",new Capacity("4","1000"),new Shedule(12,3,4),true,1,true,true,true,true);
        westministerRentalVehicleManager.addVehicle(mb);
        assertEquals("Checking if Vehicle exists",true,westministerRentalVehicleManager.getVehicles().contains(mb));
        westministerRentalVehicleManager.deleteVehicle(mb);
        assertEquals("Removing a vehicle from the list",2,westministerRentalVehicleManager.Size());

    }
    @Test
    public void testSizeAfter(){
        System.out.println("Running Test Check Vehicle After Vehicle Removed ...");
        assertEquals("Checking Vehicle Size",2,westministerRentalVehicleManager.Size());
    }


    @Test
    public void printListOfVehicles() {
        System.out.println("Running Test to Check if all Elements are added to array List to be Printed...");
        assertEquals("Printing the List Of Vehicles According to Size ",2,westministerRentalVehicleManager.Size());

    }

    @Test
    public void sizeOfDatabase(){
        System.out.println("Running Test Check Before Vehicle Was Added to Database...");
        assertEquals("The Documents Added to the Database",(westministerRentalVehicleManager.DatabaseSize()-2)+2,westministerRentalVehicleManager.DatabaseSize());
    }

    @Test
    public void CheckifValueExistsInDatabase(){
        System.out.println("Running to Check and see if Vehicle Exists in Database ");
        MotorBike mb;
        mb = new MotorBike("TVS","Jupiter","124312","07734",new Capacity("4","1000"),new Shedule(12,3,4),true,1,true,true,true,true);
        assertEquals("Checking Databse for Value",true,activityExistsMB(database,mb.getPlateNo()));
    }


    @Test
    public void saveVehicle() {
        System.out.println("Running Test Check After Vehicle Was Added to Database...");
        MotorBike mb;
        mb = new MotorBike("TVS","Jupiter","124312","07734",new Capacity("4","1000"),new Shedule(12,3,4),true,1,true,true,true,true);
        if(!activityExistsMB(database,mb.getPlateNo())){
            Document document = new Document("type","Motor Bike").append("make",mb.getMake()).append("model",mb.getModel()).append("plateNo",mb.getPlateNo()).append("contactNumber",mb.getContactNo()).append("seatCapacity",mb.getCapacity().getSeatCapacity()).append("engineCapacity",mb.getCapacity().getEngineCapacity()).append("dateAdded",mb.getShedule().getDay()+"/"+mb.getShedule().getMonth()+"/"+mb.getShedule().getYear()).append("available",mb.getAvailable()).append("transmission",mb.getTransmission()).append("haveMuffler", mb.isMuffler()).append("engineState", mb.isEngineState()).append("haveCarrier", mb.isHaveCarrier());
            collection1.insertOne(document);
        }

        assertEquals("After the Documents Were Saved to the Database",(westministerRentalVehicleManager.DatabaseSize()-3)+3,westministerRentalVehicleManager.DatabaseSize());


    }

    @Test
    public void updateVehicle() {
        System.out.println("Running Test Check to See if Vehicle Was Updated...");
        Car vehicle = new Car("Honda","Civic","CAC 1234","0773753001",new Capacity("4","1000"),new Shedule(12,8,2001),true,100,"4",true);
        System.out.println("Before Updating ..    " + westministerRentalVehicleManager.getVehicles().get(0));
        System.out.println("After Updating..");
        Car vehicle1 = new Car("Honda","Civic","CAC 1234","0773753002",new Capacity("4","1000"),new Shedule(12,10,2001),true,100,"4",true);
        westministerRentalVehicleManager.getVehicles().set(0,vehicle1);
        System.out.println(westministerRentalVehicleManager.getVehicles().get(0));

        assertEquals(westministerRentalVehicleManager.getVehicles().get(0),vehicle1);

    }

    public static boolean activityExistsCar(MongoDatabase db, String plate) {
        FindIterable<Document> iterable = db.getCollection("Car").find(new Document("plateNo", plate));
        return iterable.first() != null;
    }
    public static boolean activityExistsMB(MongoDatabase db, String plate) {
        FindIterable<Document> iterable = db.getCollection("Motor Bike").find(new Document("plateNo", plate));
        return iterable.first() != null;
    }
}