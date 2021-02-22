package org.inheritance;

/**
 * Motocycle extends class Vehicle, which means that it will contain all attributes and all methods from class Vehicle,
 * but we can override them - but in this case we won't
 */
public class Motocycle extends Vehicle {

    public void newMethod() {
        System.out.println("Motocycle class does not Override any method or attribute from its parent class");
        System.out.println("Therefore, if we access these attributes or methods, parent class implementation will be used");
        System.out.println("But we can implement more methods or attributes in this class, that are not in other classes");
    }
}
