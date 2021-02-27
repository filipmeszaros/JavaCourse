package org.syntax;

/**
 * When executing Java code, different errors can occur:
 * coding errors made by the programmer, errors due to wrong input, or other unforeseeable things.
 *
 * When an error occurs, Java will normally stop and generate an error message.
 * The technical term for this is: Java will throw an exception (throw an error).
 *
 * Try block     - block of code to be tested for errors while it is being executed (for code that can fail)
 * Catch block   - block of code to be executed, if an error occurs in the try block (to handle failure)
 * Finally block - block of code to be executed after try/catch, regardless of the result (for code cleanup, e.g. close DB)
 *
 * You can catch multiple exception types, but you need to start catching them from most specific ones, to least specific ones.
 * Why? Because all Exceptions are extending class Exception, so if you catch exception of type Exception, all exceptions will be caught.
 * Example1: ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException extends RunTimeException extends Exception
 * Example2: SQLSyntaxErrorException extends SQLNonTransientException extends SQLException extends Exception
 * Example3: FileNotFoundException extends IOException extends Exception
 */
public class Exceptions {

    public static void main(String... args) {
        System.out.println("----- Exception catching -----");
        exceptionCatchingExample();
        System.out.println("----- Exception details -----");
        exceptionDetailsExample();
        System.out.println("----- Exception throwing -----");
        try {
            exceptionThrowingExample();
        } catch (NotOldEnoughException e) {
            System.out.println("Our custom exception was thrown");
        }
    }

    /**
     * In this example we will do something stupid (e.g. divide by zero, or access non-existing index of array) and catch an exception.
     * Based on our problem, different Exception type is caught.

     */
    public static void exceptionCatchingExample() {
        int[] myNumbers = {1, 2, 3};

        //surround block of code that can throw an error with TRY block
        try {
            System.out.println("Try to print non-existing index or divide by zero: " + myNumbers[1]/0);
        //catch first most specific exception type
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught: you tried to print non-existing index, didn't you?");
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: you divided by zero, didn't you?");
        //catch least specific exception type
        } catch (Exception e) {
            System.out.println("Other exception caught: " + e.getMessage());
        //process a block that will be executed always, even when exception is thrown or not
        } finally {
            System.out.println("The 'try/catch' is finished");
        }
    }

    /**
     * Will print details of exception
     */
    public static void exceptionDetailsExample() {
        try {
            int a = 5/0;
        } catch (ArithmeticException exceptionName) {
            System.out.println("Exception getMessage(): " + exceptionName.getMessage());
            System.out.println("Exception toString(): " + exceptionName.toString());
            System.out.println("Exception getLocalizedMessage(): " + exceptionName.getLocalizedMessage());
            System.out.println("Exception getCause(): " + exceptionName.getCause());
            System.out.println("Exception getStackTrace(): " + exceptionName.getStackTrace());
        }
    }

    /**
     * Will throw our custom exception. When method throws an exception, it must be stated in method name with keyword THROWS
     */
    public static void exceptionThrowingExample() throws NotOldEnoughException {
        int age = 12;
        if (age < 18) {
            throw new NotOldEnoughException("Access denied - You must be at least 18 years old.");
        }
        else {
            System.out.println("Access granted - You are old enough!");
        }
    }
}
