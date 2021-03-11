package org.designpatterns.singleton;

/**
 * This class demonstrates usage of Singleton OOP design pattern.
 */
public class SingletonExample {
    public static void main(String[] args) {

        //Get only object available
        Singleton mySingleton = Singleton.getInstance();

        //Illegal construct: Compile time error
        //This will fail because constructor is private
        //Singleton secondSingleton = new Singleton();

        //Call method of Singleton class
        mySingleton.simpleMethod();

        //Another way of calling methods is like this
        Singleton.getInstance().connectDatabase();
        Singleton.getInstance().simpleMethod();
        Singleton.getInstance().disconnectDatabase();
    }
}
