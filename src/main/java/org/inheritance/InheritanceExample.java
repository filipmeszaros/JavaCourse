package org.inheritance;

/**
 * Inheritance is Java mechanism allowing to inherit attributes and methods from one class to another.
 * Java class does not support multiple inheritance (where a class can inherit properties of more than one parent class)
 *
 * Super keyword - refers to object of parent class
 * Method overriding - re-implementing method with identical name and parameters of parent class in its child class
 */
public class InheritanceExample {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();        //create object Vehicle
        Car car = new Car();                    //create object Car
        Truck truck = new Truck();              //create object Truck
        Motorcycle motorcycle = new Motorcycle();  //create object Motorcycle that is not Overriding anything

        System.out.println("Vehicle variable: " + vehicle.brand);
        System.out.println("Car variable: " + car.brand);
        System.out.println("Truck variable: " + truck.brand);
        System.out.println("Motorcycle variable: " + motorcycle.brand); //attribute from class Vehicle will be used

        vehicle.describeYourself();            //call function of parent
        car.describeYourself();                //call function of child
        car.describeYourselfByCallingParent(); //call function of child that calls function of parent
        truck.describeYourself();              //call function of other child
        motorcycle.describeYourself();          //call function of child that does not have method implemented, and therefore parent method is used

        motorcycle.newMethod();

        //We can even create object of subclass and convert it to superclass object
        //Because we will be using superclass, attribute brand will be taken out from superclass
        Vehicle superclass = new Car();
        System.out.println(superclass.brand);

        //We cannot do it other way, create object of superclass, and convert it to subclass
        //That will throw compilation error
        //Car subclass = new Vehicle();  //This will throw compilation error
    }
}
