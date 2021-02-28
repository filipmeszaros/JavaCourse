package org.syntax;

/**
 * Math class from java.lang package provides some mathematical equations
 */
public class Math {
    public static void main(String... args) {
        System.out.println("Min of 5 & 10: " + java.lang.Math.max(5,10));
        System.out.println("Max of 5 & 10: " + java.lang.Math.max(5,10));
        System.out.println("Sqrt of 64: " + java.lang.Math.sqrt(64));
        System.out.println("Abs of -4.7" + java.lang.Math.abs(-4.7));
        System.out.println("Random: " + java.lang.Math.random()) ;
        System.out.println("Random from 0 to 100: " + java.lang.Math.random() * 101) ;
    }
}
