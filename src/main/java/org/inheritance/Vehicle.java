package org.inheritance;

/**
 * Object Vehicle will be extended from other classes, like Car/Motorcycle/Truck.
 * These classes will provide new implementation of method describeYourself() by method overriding, and new value of attribute brand.
 * Method overriding - re-implementing method with identical name and parameters of parent class in its child class
 */
public class Vehicle {
    protected String brand = "Vehicle";    // Vehicle attribute

    public void describeYourself() {       // Vehicle method
        System.out.println("describeYourself() method of class Vehicle - I am a superclass Vehicle");
    }
}
