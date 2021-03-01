package org.inheritance;

/**
 * Car extends class Vehicle, which means that it will contain all attributes and all methods from class Vehicle,
 * but we can override them.
 * Method overriding - re-implementing method with identical name and parameters of parent class in its child class
 */
public class Car extends Vehicle {

    protected String brand = "Car";

    @Override
    public void describeYourself() {       // Car method, which is Overridden from class Vehicle
        System.out.println("describeYourself() method of class Car - I am a subclass Car");
    }

    public void describeYourselfByCallingParent() {
        super.describeYourself();         //super refers to object of parent class - we call function describeYourself() of parent (Vehicle)
    }
}
