package org.abstraction;

/**
 * In Java, the keyword static indicates that the particular member belongs to a type itself (Class),
 * rather than to an instance of that type (Object).
 *
 * Terminology:
 * - Class           - template to create an object   (e.g. class Car)
 * - Object          - instance of class              (e.g. Car c = new Car())
 * - Static variable - variable that can be accessed without creating an object of the class first
 *                   - variable that is shared between all objects
 *                   - usage: for constants, for WebDriver (so that separate WebDriver won't be created for each test - won't work in parallel test execution)
 * - Static method   - method that can be accessed without creating an object of the class first
 * - Static class    - to create nested classes (class within a class)
 *                   - usage: way of grouping elements that are only going to be used in one place -> more readable
 */
public class StaticClassExample {

    public static String staticVariable = "Static variable";
    public String nonStaticVariable = "Non-Static variable";
    public static int numberOfObjects = 0;

    public static final double PI = 3.141592654;

    /**
     * This is a constructor, that is called after creating an object of class StaticClassExample.
     * Because static variables are shared across all objects of the same class, we will use it to track number of created objects
     */
    StaticClassExample() {
        numberOfObjects++;  //this variable is shared across all objects
    }

    public static void main(String[] args) {
        System.out.println("----- Example of static variables -----");
        staticVariables();
        System.out.println("----- Example of static methods -----");
        staticMethods();
        System.out.println("----- Example of static classes -----");
        staticClasses();

        System.out.println("----- Example of static variable to count number of objects created -----");
        System.out.println("Number of objects of class StaticClassExample() created so far: " + StaticClassExample.numberOfObjects);
        StaticClassExample sce = new StaticClassExample();
        System.out.println("Number of objects of class StaticClassExample() after creating 3rd object: " + StaticClassExample.numberOfObjects);
        StaticClassExample sce2 = new StaticClassExample();
        System.out.println("Number of objects of class StaticClassExample() after creating 4th object: " + StaticClassExample.numberOfObjects);
    }

    /**
     * Method to demonstrate example of using static variables
     */
    public static void staticVariables() {
        System.out.println("Accessing static variable without creating an object of class: " + StaticClassExample.staticVariable);

        StaticClassExample obj = new StaticClassExample();
        System.out.println("Accessing non-static variable with need of creating an object first: " + obj.nonStaticVariable);

        //System.out.println("This won't work and therefore is commented out: " + StaticClassExample.nonStaticVariable);
    }

    /**
     * Method to demonstrate example of using static methods
     */
    public static void staticMethods() {
        System.out.println("Calling a static method without creating an object of class");
        StaticClassExample.staticMethodExample();

        StaticClassExample obj = new StaticClassExample();
        System.out.println("Calling a non-static method with need of creating an object first");
        obj.nonStaticMethodExample();

        //StaticClassExample.nonStaticMethodExample();  //this won't work and therefore is commented out
    }

    /**
     * Method to demonstrate example of using static classes (nested classes)
     */
    public static void staticClasses() {
        /* To create instance of nested class we didn't need the outer
         * class instance but for a regular nested class you would need
         * to create an instance of outer class first
         */
        StaticClassExample.MyNestedClass nestedObject = new StaticClassExample.MyNestedClass();
        nestedObject.printConstant();
    }

    /**
     * Simple static method
     */
    public static void staticMethodExample() {
        System.out.println("This is an example of static method");
    }

    /**
     * Simple non-static method
     */
    public void nonStaticMethodExample() {
        System.out.println("This is an example of non-static method");
    }

    /**
     * Static classes are used to create nested classes (class inside a class)
     */
    static class MyNestedClass{
        //non-static method
        public void printConstant() {

            /* If you make a PI variable of outer class non-static then you will get compilation error because
             * a nested static class cannot access non-static members of the outer class.
             */
            System.out.println("Printing constant PI: " + PI);
        }
    }
}
