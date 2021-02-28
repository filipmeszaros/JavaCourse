package org.inheritance;

/**
 * Car extends class Vehicle, which means that it will contain all attributes and all methods from class Vehicle,
 * but we can override them
 */
public class Car extends Vehicle {

    protected String brand = "Car";

    @Override
    public void describeYourself() {       // Car method, which is Overridden from class Vehicle
        System.out.println("I am a subclass Car");
    }
}
