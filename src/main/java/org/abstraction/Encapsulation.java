package org.abstraction;

/**
 * The meaning of Encapsulation, is to make sure that "sensitive" data is hidden from users.
 * To achieve encapsulation, you must:
 *  - declare class variables/attributes as private (accessible only from within a class)
 *  - provide public get and set methods to access and update the value of a private variables
 *
 *  Why encapsulation?
 *  - better control of class attributes and methods
 *  - class attributes can be made read-only (if you only use the get method), or write-only (if you only use the set method)
 *  - flexible: the programmer can change one part of the code without affecting other parts
 *  - increased security of data
 */
public class Encapsulation {

    private String name;
    private int age;

    /**
     * This method provides an example of encapsulation.
     * Imagine that this method is called from different class/object.
     * Encapsulation provides a way of hiding variable name and age from object of class Encapsulation.
     * You won't be able to access class variables from object directly (e.g. object.name or object.age) because they are private,
     * but you need to call getName() or getAge() methods which are public, to access class variables indirectly.
     */
    public static void main(String[] args) {
        Encapsulation object = new Encapsulation();
        object.setAge(24);
        object.setName("John");

        System.out.println("Variable age of object is: " + object.getAge());
        System.out.println("Variable name of object is: " + object.getName());

        object.setAge(25);
        System.out.println("Updated variable age of object is: " + object.getAge());
    }

    //public function will set private class variable name
    public void setName(String newName) {
        this.name = newName;
    }

    //public function will set private class variable age
    public void setAge(int newAge) {
        this.age = newAge;
    }

    //public function will get private class variable name
    public String getName() {
        return this.name;
    }

    //public function will get private class variable age
    public int getAge() {
        return this.age;
    }
}
