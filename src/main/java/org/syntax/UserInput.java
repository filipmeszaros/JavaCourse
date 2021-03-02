package org.syntax;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Scanner class is used to get user input, and it is found in the java.util package.
 * If you enter wrong input, you will get an exception, so your program should be handling these exceptions.
 * You can use multiple methods to scan for user input, e.g.:
 *   - nextBoolean() - reads a boolean value from the user
 *   - nextByte()    - reads a byte    value from the user
 *   - nextDouble()  - reads a double  value from the user
 *   - nextFloat()   - reads a float   value from the user
 *   - nextInt()     - reads a int     value from the user
 *   - nextLine()    - reads a String  value from the user
 *   - nextLong()    - reads a long    value from the user
 *   - nextShort()   - reads a short   value from the user
 */
public class UserInput {
    public static void main(String[] args) {
        Scanner scannerObject = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your name and press enter");

        String name = scannerObject.nextLine();  // Read user input
        System.out.println("Entered name is: " + name);  // Output user input

        System.out.println("Enter your height [cm]");
        try {
            int height = scannerObject.nextInt();
            System.out.println("Entered height is: " + height + " [cm]");
        } catch (InputMismatchException e) {
            System.err.println("Wrong height entered! Only integer numbers are expected");
        }
    }
}
