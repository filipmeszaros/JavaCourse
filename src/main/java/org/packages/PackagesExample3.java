package org.packages;

import org.packages.packageA.ClassName;
import org.packages.packageC.AccessModifiersClass;

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
public class PackagesExample3 extends AccessModifiersClass {
    public static void main(String[] args) {
        ClassName classObject = new ClassName();
        classObject.method();

        PackagesExample3 accessExample = new PackagesExample3(); //Create child object

        //Only PUBLIC and PROTECTED access modifiers attribute and methods will be working,
        //because PackagesExample3 is in different package, but it is extending AccessModifiersClass
        //System.out.println("Private attribute: " + accessExample.privateAttribute);     //Won't work - not in same class
        //System.out.println("Default attribute: " + accessExample.defaultAttribute);     //Won't work - not in same package
        System.out.println("Protected attribute: " + accessExample.protectedAttribute);   //Is working - we are a subclass
        System.out.println("Public attribute: " + accessExample.publicAttribute);

        //accessExample.privateMethod();     //Won't work - not in same class
        //accessExample.defaultMethod();     //Won't work - not in same package
        accessExample.protectedMethod();     //Is working - we are a subclass
        accessExample.publicMethod();
    }
}
