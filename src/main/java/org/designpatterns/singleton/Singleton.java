package org.designpatterns.singleton;

/**
 * Singleton OOP design pattern involves a single class which is responsible to create an object
 * while making sure that only single object gets created.
 * Usage: for objects that can be created only once, e.g. (Database connection, webdriver, screenshot creator, etc)
 *
 * How is it done?
 *  - make constructor private
 *  - make static class variable containing instance of class
 *  - make public method to access this class variable
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    /*
    By making constructor private, you would not be able to call
    Singleton s = new Singleton() from other classes except this one
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }

    public void simpleMethod() {
        System.out.println("Simple method of Singleton class");
    }

    public void connectDatabase() {
        System.out.println("Connect database called...");
    }

    public void disconnectDatabase() {
        System.out.println("Disconnect database called...");
    }
}
