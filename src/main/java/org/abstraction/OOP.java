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
 *   - "this" keyword   - keyword this refers to the current object in a method or constructor
 *   - Constructor      - method that is called as soon as object of a class is created. Always has a name identical to name of class
 *
 * In this example, we create an class OOP that is a template for creating objects, and implement some methods and constructors.
 * and in another class (@see class {@link OOPExample}, we will create objects of this class, and demonstrate their usage
 */
public class OOP {

    /*
    Class variables - each class can have class variables, that can be used when object is created from class
    */
    public int classVariableInteger;
    public String classVariableString;

    /*
    Constructor is a method that is called identically as class name
    Constructor method is called as soon as object of class is created
    You can have multiple constructors, and based on which parameters you pass to object you are creating,
    that constructor is called.
    In this class, 4 different constructors are implemented
    Example:
    OOP obj = new OOP();         //object of class OOP is created, and empty constructor of class OOP is executed
    OOP obj = new OOP("param");  //object of class OOP is created, and constructor with one string param of class OOP is executed
    OOP obj = new OOP(42);       //object of class OOP is created, and constructor with one int param of class OOP is executed
    */
    public OOP() {
        System.out.println("Constructor OOP() of class OOP was executed");
    }

    public OOP(int number) {
        System.out.println("Constructor OOP(int number) of class OOP was executed, with number: " + number);
        classVariableInteger = number;   //we assign value passed to constructor to class variable
    }

    public OOP(String str) {
        System.out.println("Constructor OOP(String str) of class OOP was executed, with string: " + str);
        classVariableString = str;       //we assign value passed to constructor to class variable
    }

    /*
    Note: because method parameters classVariableInteger and classVariableString are called identically as class variables,
    we will have to use special keyword "this" in constructor, so that we will distinguish if we are referring to
    method variable, or class variable
     */
    public OOP(int classVariableInteger, String classVariableString) {
        System.out.println("Constructor OOP(String str) of class OOP was executed, with number " + classVariableInteger + " and string '" + classVariableString + "'");

        /*
        Important: usage of keyword "this"
        Because method parameters and class variables has identical names, we need to use this.classVariable to
        distinguish between those 2 variables. "this" always refers to current object
        Therefore "this.classVariableInteger" refers to classVariableInteger of our object
        and just "classVariableInteger" refers to int classVariableInteger parameter that is passed to constructor
        In previous constructors, we had different names for these 2 variables (str + number) so we need not to use "this"
        */
        this.classVariableInteger = classVariableInteger;     //we assign value passed to constructor to class variable
        this.classVariableString = classVariableString;       //we assign value passed to constructor to class variable
    }

    /*
    Class methods are functions that can be executed when object is created from a class
    In this class, 4 different methods are implemented.
    printClassVariables() - prints class variables
    printWarning()        - prints warning message
    printStatusMessage()  - prints message passed to method (function)
    sumOfNumbers()        - returns sum of 2 numbers passed to a method (function)
    isPositiveNumber()    - returns true if number is >= 0, otherwise returns false
    */
    public void printClassVariables() {
        int methodVariable = 0; //this is a method variable that is readable only within this method
        System.out.println("Class variables are: '" + classVariableString + "' and " + classVariableInteger);
    }

    public void printWarning() {
        int methodVariable = 0; //this is a method variable that is readable only within this method
        System.out.println("Warning! This is printed in method of object OOP");
    }

    public void printStatusMessage(String message) {
        String methodVariable = "This is a method variable that is readable only within this method";
        System.out.println(message);
    }

    public int sumOfNumbers(int num1, int num2) {
        String methodVariable = "This is a method variable that is readable only within this method";
        return num1 + num2;
    }

    public boolean isPositiveNumber(int number) {
        String methodVariable = "This is a method variable that is readable only within this method";

        if (number >= 0)
            return true;
        else
            return false;
    }
}
