package org.abstraction;

/**
 * Abstraction hides implementation details from user.
 * Data abstraction is the process of hiding certain details and showing only essential information to the user.
 * Abstraction can be achieved with either abstract classes or interfaces.
 *
 * Abstract class  - class that cannot be used to create objects (to access an abstract class, it must be inherited from another class)
 *                 - can contain list of methods with their parameters and return values, without an implementation of these methods
 *                 - can contain methods, that are implemented
 *                 - each abstract class can be implemented in different java class, that uses "extends AbstractClassName" in class definition
 * Abstract method - method that can only be used in an abstract class, and it does not have a body (body is provided by the subclass)
 *
 * Abstract class - provides partial abstraction (some methods can be implemented, while another don't have to be implemented in abstract class)
 *                - @see difference between Interface in {@link InterfaceExample}
 */
public abstract class AbstractClassExample {
    //method that is not implemented
    public abstract void abstractClassMethodUnimplemented();

    //method that is implemented
    public void abstractClassMethodImplemented() {
        System.out.println("Implementation of method in Abstract class");
    }
}
