package org.abstraction;

/**
 * Abstraction hides implementation details from user.
 * Interface  - contains list of methods with their parameters and return values, without an implementation of these methods
 *            - each interface can be implemented in different java class, that uses "implements InterfaceName" in class definition
 *            - to access the interface methods, the interface must be "implemented"
 *            - interface variables must be public, and must be defined in interface
 * Interface class - provides full abstraction (methods cannot be implemented in interface)
 *                 - @see difference between Abstract class in {@link AbstractClassExample}
 */
public interface InterfaceExample {
    public int interfaceVariable = 42;

    void interfaceMethodUnimplemented();
}
