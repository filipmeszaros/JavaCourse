package org.packages.packageC;

/**
 * Package in Java is used to group related classes (think of it as a folder in a file directory)
 * We use packages to avoid name conflicts.
 * There can be multiple classes in different package(see packageA,packageB).
 * Based on which class from which package we import, we can used it.
 *
 * Access modifiers (private/default/public/protected) specifies the accessibility or scope of a
 * field, method, constructor, or class.
 *
 * PRIVATE   = accessible within our class only
 * DEFAULT   = accessible within our class + within package
 * PROTECTED = accessible within our class + within package + outside package by subclass only
 * PUBLIC    = accessible from everywhere
 */
public class PackagesExample2 {

    public static void main(String[] args) {
        AccessModifiersClass accessExample = new AccessModifiersClass();

        //PUBLIC, PROTECTED, DEFAULT attributes and methods will be working, because we are in
        //the same package as class AccessModifiersClass
        //PRIVATE attributes and methods won't work, because they are used only WITHIN the same class.
        //System.out.println("Private attribute: " + accessExample.privateAttribute);  //Won't work - not in same class
        System.out.println("Default attribute: " + accessExample.defaultAttribute);
        System.out.println("Protected attribute: " + accessExample.protectedAttribute);
        System.out.println("Public attribute: " + accessExample.publicAttribute);

        //accessExample.privateMethod();  //Won't work - not in same class
        accessExample.defaultMethod();
        accessExample.protectedMethod();
        accessExample.publicMethod();
    }
}
