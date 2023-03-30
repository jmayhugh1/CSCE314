
import java.util.ArrayList;
import java.util.Comparator;

public class Vehicle {
//    Section 2.1, Exercise 2.1 
//fields for current speed direction and owner name
    private int speed;
    private int direction;
    private String owner;
    //constants for turning left and right
    public static final String TURN_LEFT = "left";
    public static final String TURN_RIGHT = "right";

//    Section 2.2, Exercise 2.3 
//Add a static field to your Vehicle class to hold the next vehicle 
//identification number, and a non-static field to the Vehicle class 
//to hold each car's ID number.
//statc field to hold the next vehicle ID number
    private static int nextID = 0;
//increment the next ID number everytime a new vehicle is created
    
//instance field to hold the vehicle ID number
    private int vehicleID;
    
public Vehicle(){
    speed = 0;
    direction = 0;
    owner = "N/A";
    vehicleID = nextID;
    nextID++;
}
public Vehicle(String owner){
    speed = 0;
    direction = 0;
    this.owner = owner;
    vehicleID = nextID;
    nextID++;

}
public Vehicle(int speed, int direction, String owner){
    this.speed = speed;
    this.direction = direction;
    this.owner = owner;
    vehicleID = nextID;
    nextID++;
}
//    Section 2.6, Exercise 2.13 
//accesor methods for the fields
    public int getSpeed(){
        return speed;
    }
    public int getDirection(){
        return direction;
    }
    public String getOwner(){
        return owner;
    }
    public int getVehicleID(){
        return vehicleID;
    }
    public static int getNextID(){
        return nextID;
    }
//mutator methods for the fields
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    
    //Section 2.5, Exercise 2.7 
    //Section 2.6, Exercise 2.9
    //Section 2.6, Exercise 2.10 on page 60
    public String toString(){
        return "This vehicle is owned by " + owner + " and is going " + speed + " mph in direction " + direction + " and has an ID: " + vehicleID + ".";
    }
    //Section 2.6, Exercise 2.15
    public void changeSpeed(int speed){
        this.speed = speed;
    }
    //Section 2.8, Exercise 2.17
    //turn method one that takes a number of degress and one that takes either of the constants Vehicle.TURN_LEFT or Vehicle.TURN_RIGHT
    public void turn(int degrees){
        direction += degrees;
        //if the direction is greater than 360 then subtract 360
        if(direction > 360){
            direction -= 360;
        }
    }
  public void turn(String direction){
        if(direction.equals(TURN_LEFT)){
            this.direction -= 90;
        }
        else if(direction.equals(TURN_RIGHT)){
            this.direction += 90;
        }
        //if the direction is greater than 360 then subtract 360
        if(this.direction > 360){
            this.direction -= 360;
        }
    }
}
//section 3.1 Exercise 3.1
//create an extneded class called passenger vehicle that adds capavility for counting number of seats available in a car
// and the number of passengers currently in the car
//create a new class called PassengerVehicle that extends Vehicle
//also hace the PassengerVehicle implement the Comparable interface
class PassengerVehicle  extends Vehicle implements Comparable<PassengerVehicle>{
    //add fields for number of seats and number of passengers
    private int seats;
    private int passengers;
    //add a constructor that takes the number of seats and the owner name
    public PassengerVehicle(int seats, String owner){
        super(owner);
        this.seats = seats;
        passengers = 0;
    }
    //add a method to add a passenger
    public void addPassenger(){
        if(passengers < seats){
            passengers++;
        }
        else{
            System.out.println("There are no more seats available.");
        }
    }
    //add a method to remove a passenger
    public void removePassenger(){
        if(passengers > 0){
            passengers--;
        }
        else{
            System.out.println("There arent any passengers.");
        }
    }
    //add a method to return the number of seats available
    public int seatsAvailable(){
        return seats - passengers;
    }
    //add a toString method
    public String toString(){
        return "This vehicle is owned by " + getOwner() + " and is going " + getSpeed() + " mph in direction " + getDirection() + " and has an ID: " + getVehicleID() + " and has " + seatsAvailable() + " seats available.";
    }
    //compareto
    public int compareTo(PassengerVehicle other){
        if(this.seatsAvailable() > other.seatsAvailable()){
            return -1;
        }
        else if(this.seatsAvailable() < other.seatsAvailable()){
            return 1;
        }
        else{
            return 0;
        }
    }
    //main method to test
    public static void main(String[] args){
        PassengerVehicle car1 = new PassengerVehicle(4, "Craig");
        car1.changeSpeed(90);
        car1.setDirection(0);
        car1.addPassenger();
        car1.addPassenger();
        PassengerVehicle car2 = new PassengerVehicle(6, "Doug");
        car2.changeSpeed(100);
        car2.setDirection(20);
        car2.addPassenger();
        car2.addPassenger();
        PassengerVehicle car3 = new PassengerVehicle(8, "Johnny");
        car3.changeSpeed(110);
        car3.setDirection(40);
        car3.addPassenger();
        car3.addPassenger();
        car3.addPassenger();
        PassengerVehicle car4 = new PassengerVehicle(10, "Bob");
        car4.changeSpeed(120);
        car4.setDirection(60);
        car4.addPassenger();
        car4.addPassenger();
        car4.addPassenger();

        PassengerVehicle car5 = new PassengerVehicle(12, "Sally");
        car5.changeSpeed(130);
        car5.setDirection(80);
        car5.addPassenger();
        car5.addPassenger();
        car5.addPassenger();
        car5.addPassenger();
        //create a list and store the cars in it
        ArrayList<PassengerVehicle> cars = new ArrayList<PassengerVehicle>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        cars.sort(null);
        for(PassengerVehicle car : cars){
            System.out.println(car);
        }

    }

        
}
class VehicleTest
{
    public static void main(String[] args)
    {
       //create 5 vehicles with the no-arg constructor
       //print out thier ID numbers
        Vehicle car1 = new Vehicle();
        car1.changeSpeed(90);
        car1.setDirection(0);
        car1.setOwner("Craig");
        System.out.println(car1);
        Vehicle car2 = new Vehicle();
        car2.changeSpeed(100);
        car2.setDirection(20);
        car2.setOwner("Doug");
        System.out.println(car2);
        Vehicle car3 = new Vehicle();
        car3.changeSpeed(110);
        car3.setDirection(40);
        car3.setOwner("Johnny");
        System.out.println(car3);
        Vehicle car4 = new Vehicle();
        car4.changeSpeed(120);
        car4.setDirection(60);
        car4.setOwner("Franky");
        System.out.println(car4);
        Vehicle car5 = new Vehicle();
        car5.changeSpeed(130);
        car5.setDirection(80);
        car5.setOwner("Sally");
        System.out.println(car5);
        //create 5 vehicles with the 1-arg constructor
        Vehicle car6 = new Vehicle("John");
        car6.changeSpeed(30);
        car6.setDirection(40);
        System.out.println(car6);
        Vehicle car7 = new Vehicle("Frank");
        car7.changeSpeed(40);
        car7.setDirection(50);
        System.out.println(car7);
        Vehicle car8 = new Vehicle("Ty");
        car8.changeSpeed(50);
        car8.setDirection(60);
        System.out.println(car8);
        Vehicle car9 = new Vehicle("Suraj");
        car9.changeSpeed(60);
        car9.setDirection(70);
        System.out.println(car9);
        Vehicle car10 = new Vehicle("Josh");
        car10.changeSpeed(70);
        car10.setDirection(80);
        System.out.println(car10);
        //test the turn method
        System.out.println("-------------Testing the turn method-------------");
        car1.turn(90);
        System.out.println(car1);
        car2.turn(180);
        System.out.println(car2);
        car3.turn(270);
        System.out.println(car3);
        car4.turn(360);
        System.out.println(car4);
        car5.turn(Vehicle.TURN_LEFT);
        System.out.println(car5);
        car6.turn(Vehicle.TURN_RIGHT);
        System.out.println(car6);




    }
}