package org.abstraction;

/**
 * Abstraction hides implementation details from user.
 * Main function in this class demonstrates usage of interface and usage of abstract class (implemented + not implemented methods)
 */
public class AbstractionExample extends AbstractClassExample implements InterfaceExample {

    //Run this function
    public static void main(String[] args) {
        //create object of this class and run each function
        AbstractionExample ae = new AbstractionExample();
        ae.abstractClassMethodImplemented(); //we use implementation from AbstractClassExample
        ae.abstractClassMethodUnimplemented(); //we use implementation from this class
        ae.interfaceMethodUnimplemented(); //we use implementation from this class
    }

    //because we are extending AbstractClassExample, we must provide implementation of all methods in abstract class description
    @Override
    public void abstractClassMethodUnimplemented() {
        System.out.println("Implementation of abstract class method for AbstractClassExample");
    }

    //because we are implementing InterfaceExample, we must provide implementation of all methods in interface description
    @Override
    public void interfaceMethodUnimplemented() {
        System.out.println("Implementation of interface method for InterfaceExample");
    }
}