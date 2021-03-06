package org.abstraction;

/**
 * OOP stands for Object-Oriented Programming.
 * Procedural programming is about writing procedures or methods that perform operations on the data,
 * while object-oriented programming is about creating objects that contain both data and methods.
 * Two main aspects of OOP are Classes and Objects. In OOP, everything is associated with classes and objects.
 *
 * Advantages:
 *   - faster and easier to execute
 *   - provides clear structure for the programs
 *   - code is easier to maintain, modify and debug
 *   - helps to keep Java code DRY (Don't Repeat Yourself) - reduces repetition of code
 *
 * Basic OOP terminology:
 *   - Class    - is a template (blueprint) for creating objects, that contains variables and methods (function)
 *   - Object   - is an instance of a class, that works with variables and run methods
 *   - Class variables  - data variables (or attributes) that can be used when object is created from a class
 *   - Class methods    - functions that can be executed when object is created from a class
 *   - Method parameter - parameter that is passed to a method (can be int, String, or whatever else)
 *   - Method variable  - variable that is available only within a method. That's why multiple variables with identical name can be available in a single class
 *   - "this" keyword   - keyword this refers to the current object in a method or constructor (@see class {@link OOP})
 *   - Constructor      - method that is called as soon as object of a class is created. Always has a name identical to name of class

 * In this example, we create an objects of class OOP (@see class {@link OOP}) and work with them
 * We will demonstrate how objects are created, how constructors are called, and how methods can be called from object.
 */
public class OOPExample {

    /*
    Class variables - each class can have class variables, that can be used when object is created from class
    */
    private int classVariableInteger;
    private String classVariableString;

    //This method will be executed when we run class OOPExample
    public static void main(String[] args) {

        System.out.println("Name of this class is: " + OOPExample.class.getName());

        /*
        We create object 'object1' of class 'OOP' with no parameters passed to constructor
        This is why output to console is printed out, because constructor is printing this output
        Then we call method that prints class variables that were set up in object by calling constructor
        */
        OOP object1 = new OOP();        //pass no variables to constructor
        object1.printClassVariables();  //will print 'null' and 0, as default values

        /*
        We create object 'object2' of class 'OOP' with int parameter passed to constructor
        This is why different output to console is printed out, because different constructor is printing this output
        Then we call method that prints class variables that were set up in object by calling constructor
        */
        OOP object2 = new OOP(42); //pass int variable to constructor
        object2.printClassVariables();    //will print 'null' (default value of String) and 42 (value passed to object)

        /*
        We create object 'object3' of class 'OOP' with String parameter passed to constructor
        This is why different output to console is printed out, because different constructor is printing this output
        Then we call method that prints class variables that were set up in object by calling constructor
        */
        OOP object3 = new OOP("third object created");  //pass String variable to constructor
        object3.printClassVariables();    //will print 'third object created' (value passed to object) and 0 (default value for int)

        /*
        We create object 'object4' of class 'OOP' with both String and int parameters passed to constructor
        This is why different output to console is printed out, because different constructor is printing this output
        Then we call method that prints class variables that were set up in object by calling constructor
        */
        OOP object4 = new OOP(1000, "forth object created");  //pass both variables to constructor
        object4.printClassVariables();    //will print 'forth object created' and 1000 (both are values passed to constructor)

        /*
        Now we will take one of these objects (object1) and run each method of this object.
        All of these methods are implemented in class OOP.java
        */
        object4.printWarning();   //warning will be printed to console
        object4.printStatusMessage("STATUS message text");  //status message will be printed to console
        boolean result = object4.isPositiveNumber(4);       //we will store result of method isPositiveNumber(4) to a variable
        System.out.println("Result of method isPositiveNumber(4): " + result);
        int sumOfNumbers = object4.sumOfNumbers(10, 15);    //we will store result of method sumOfNumbers(10, 15) to a variable
        System.out.println("Result of method sumOfNumbers(10, 15): " + sumOfNumbers);

        //Because class variables of class OOP are public, we can access them directly
        System.out.println("Printing class variable 'classVariableInteger' of object4: " + object4.classVariableInteger);
        System.out.println("Printing class variable 'classVariableString' of object4: " + object4.classVariableString);
    }
}