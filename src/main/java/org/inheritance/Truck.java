package org.inheritance;

/**
 * Truck extends class Vehicle, which means that it will contain all attributes and all methods from class Vehicle,
 * but we can override them.
 * Method overriding - re-implementing method with identical name and parameters of parent class in its child class
 */
public class Truck extends Vehicle {

    protected String brand = "Truck";

    @Override
    public void describeYourself() {       //Truck method, which is Overridden from class Vehicle
        System.out.println("describeYourself() method of class Truck - I am a subclass Truck");
    }
}
