package org.syntax;

import org.abstraction.OOP;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class demonstrates use of JavaDoc.
 * Javadoc is a tool which comes with JDK and it is used for generating Java code documentation in HTML format
 * from Java source code, which requires documentation in a predefined format.
 * You can generate JavaDoc documentation for this class by running command "javadoc JavaDocumentation.java",
 * or from IntelliJ menu Tools/Generate JavaDoc.
 *
 * You can link to other classes with {@link NotOldEnoughException}.
 * You can link to other methods with {@link OOP#printClassVariables()}
 * Adds a "See Also" heading with a link or text entry that points to reference.
 * @see org.abstraction.OOPExample
 *
 * @author  Filip Meszaros
 * @version 1.0
 * @since   2021-03-03
 */
public class JavaDocumentation {

    /**
     * Class constructor specifying number of warnings printed
     */
    JavaDocumentation(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Warning");
        }
    }

    /**
     * Example of method documentation. This method will return sum of 2 integers
     *
     * @param  num1 first integer
     * @param  num2 second integer
     * @return      sum of integers passed to function
     */
    public int sumOfNumbers(int num1, int num2) {
        return num1 + num2;
    }

    /**
     * Example of method documentation. This method will scan user input for age and then return it.
     * In case of age is lower than 18, NotOldEnoughException is thrown
     * @throws InputMismatchException on input error.
     * @throws NotOldEnoughException  when entered age is lower than 18
     * @return user age typed to console
     */
    public int getUserAge() throws NotOldEnoughException, InputMismatchException {
        int age;
        Scanner scannerObject = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your age and press enter");
        try {
            age = scannerObject.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException();
        }

        if (age < 18) {
            throw new NotOldEnoughException();
        }
        return age;
    }

    /**
     * Example of deprecated function.
     * By adding deprecated annotation, we warn users using this function that it will be removed in future releases.
     * Also, we tell users what other function to use instead of this one.
     * Use {@link JavaDocumentation#getUserAge()} instead.
     */
    @Deprecated
    public void deprecatedFunction() {
        System.out.println("This function will be removed in new version");
    }
}
