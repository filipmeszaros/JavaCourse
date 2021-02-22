package org.inheritance;

/**
 * Inheritance is Java mechanism allowing to inherit attributes and methods from one class to another.
 * Java class does not support multiple inheritance (where a class can inherit properties of more than one parent class)
 */
public class InheritanceExample {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();        //create object Vehicle
        Car car = new Car();                    //create object Car
        Truck truck = new Truck();              //create object Truck
        Motocycle motocycle = new Motocycle();  //create object Motocycle that is not Overriding anything

        System.out.println("Vehicle variable: " + vehicle.brand);
        System.out.println("Car variable: " + car.brand);
        System.out.println("Truck variable: " + truck.brand);
        System.out.println("Motocycle variable: " + motocycle.brand); //attribute from class Vehicle will be used

        vehicle.describeYourself();
        car.describeYourself();
        truck.describeYourself();
        motocycle.describeYourself();  //method from class Vehicle will be used, because it is not overriden

        motocycle.newMethod();

        //We can even create object of subclass and convert it to superclass object
        //Because we will be using superclass, attribute brand will be taken out from superclass
        Vehicle superclass = new Car();
        System.out.println(superclass.brand);

        //We cannot do it other way, create object of superclass, and convert it to subclass
        //That will throw compilation error
        //Car subclass = new Vehicle();  //This will throw compilation error
    }
}
