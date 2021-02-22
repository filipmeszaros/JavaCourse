package org.inheritance;

/**
 * Truck extends class Vehicle, which means that it will contain all attributes and all methods from class Vehicle,
 * but we can override them
 */
public class Truck extends Vehicle {

    protected String brand = "Truck";

    @Override
    public void describeYourself() {       //Truck method, which is Overridden from class Vehicle
        System.out.println("I am a subclass Truck");
    }
}
