package org.syntax;

import java.util.Random;

/**
 * This is a class that demonstrates examples of how to generate pseudo random numbers.
 * There are 2 ways of generating Random stuff:
 * 1, java.lang.Math class with random() method
 * 2, java.util.Random class with methods nextInt(), nextDouble(), nextBoolean(), and others
 *
 * Because our package also contains class Math and we need to use class Math from package java.lang,
 * we need to specify class with full package name, e.g. java.lang.Math.random()
 */
public class RandomNumbers {

    static Random random = new Random();

    public static void main(String[] args) {
        // generate 10 random floating point (number with decimal places) numbers between 0 and 1
        for (int i = 0; i < 10; i++) {
            double rand = java.lang.Math.random();

            // Output is different everytime this code is executed
            System.out.println("Generating random double number between 0 - 1: " + rand);
        }

        // define the range
        int max = 10;
        int min = 1;
        int range = max - min + 1;

        // generate 10 random integer numbers within 1 to 10
        for (int i = 0; i < 10; i++) {
            int rand = (int)(java.lang.Math.random() * range) + min;

            // Output is different everytime this code is executed
            System.out.println("Generating random number between 1 - 10: " + rand);
        }

        // re-define the range
        max = 12;
        min = 7;
        range = max - min + 1;

        // generate 10 random integer numbers within 7 to 12
        for (int i = 0; i < 10; i++) {
            int rand = (int)(java.lang.Math.random() * range) + min;

            // Output is different everytime this code is executed
            System.out.println("Generating random number between 7 - 12: " + rand);
        }

        // create byte array
        byte[] bytes = new byte[15];

        // Another way of generating random Integers/Booleans/Doubles, etc. is to use Random() class from java.util package
        for (int i = 0; i < 5; i++) {
            System.out.println("Random Boolean in Java: " + random.nextBoolean());
            System.out.println("Random Integer in Java: " + random.nextInt());
            System.out.println("Random Double in Java: " + random.nextDouble());
            System.out.println("Random Float in Java: " + random.nextFloat());
            System.out.println("Random Long in Java: " + random.nextLong());
            System.out.println("Random Gaussian in Java: " + random.nextGaussian());
            // fill array of bytes with random bytes and then print this array as string
            random.nextBytes(bytes);
            System.out.println("Random Bytes in Java: " + java.util.Arrays.toString(bytes));
        }
    }
}
