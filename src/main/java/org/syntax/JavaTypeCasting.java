package org.syntax;

/**
 * Type casting is when you assign a value of one primitive data type to another type.
 *
 * 2 types of casting:
 *   1, Widening Casting (automatically) - converting a smaller type to a larger type size
 *      e.g.: byte -> short -> char -> int -> long -> float -> double
 *   2, Narrowing Casting (manually) - converting a larger type to a smaller size type
 *      e.g.: double -> float -> long -> int -> char -> short -> byte
 */
public class JavaTypeCasting {
    public static void main(String[] args) {
        wideningCasting();
        narrowingCasting();
    }

    public static void wideningCasting() {
        int myInt = 9;
        double myDouble = myInt; // Automatic casting: int to double

        System.out.println(myInt);      // Outputs 9
        System.out.println(myDouble);   // Outputs 9.0
    }

    public static void narrowingCasting() {
        double myDouble = 9.78;
        int myInt = (int) myDouble; // Manual casting: double to int

        System.out.println(myDouble);   // Outputs 9.78
        System.out.println(myInt);      // Outputs 9
    }
}
