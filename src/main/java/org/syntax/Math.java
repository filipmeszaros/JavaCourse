package org.syntax;

/**
 * Math class from java.lang package provides some mathematical equations
 * Because this class name is also Math, we need to specify full package name of class Math that is used for calculations.
 * Therefore, instead of using just Math.function(x), we need to use java.lang.Math.function(x)
 */
public class Math {
    public static void main(String... args) {
        System.out.println("Min of 5 & 10: " + java.lang.Math.max(5,10));
        System.out.println("Max of 5 & 10: " + java.lang.Math.max(5,10));
        System.out.println("Sqrt of 64: " + java.lang.Math.sqrt(64));
        System.out.println("Abs of -4.7" + java.lang.Math.abs(-4.7));
        System.out.println("Random: " + java.lang.Math.random());
        System.out.println("Random from 0 to 100: " + java.lang.Math.random() * 101);
        System.out.println("Value 5.6 rounded to nearest integer: " + java.lang.Math.round(5.6));
        System.out.println("Value 5.6 rounded DOWN to nearest integer: " + java.lang.Math.floor(5.6));
        System.out.println("Value 5.6 rounded UP to nearest integer: " + java.lang.Math.ceil(5.6));
        System.out.println("e^1 is: " + java.lang.Math.exp(1));
        System.out.println("e^5 is: " + java.lang.Math.exp(5));
        System.out.println("Log10(1000): " + java.lang.Math.log10(1000) + " (10^X == 1000?)");
        System.out.println("5^3 = " + java.lang.Math.pow(5, 3));
        System.out.println("180 degrees to radians: " + java.lang.Math.toRadians(180));
        System.out.println("3.14159 radians to radians: " + java.lang.Math.toDegrees(3.14159));
    }
}
