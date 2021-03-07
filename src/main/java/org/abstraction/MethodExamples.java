package org.abstraction;

/**
 * A method (a function) is a block of code which only runs when it is called.
 * You can pass data, known as parameters, into a method.
 * You can retrieve data, known as return value, from a method.
 * Why use methods? To reuse code: define the code once, and use it many times.
 *
 * Method description is in this format:
 * <access modifiers> <non-access modifiers> <return type> <method name> <method parameters>
 * Example:
 * public static int sumOfTwoIntegers(int number1, int number2);
 * private void printError(String errorDetails);
 *
 * Terminology:
 * access modifiers     - defines if method will be visible from other classes or packages (@see {@link org.packages.packageC.AccessModifiersClass})
 *                      - example: private/protected/public/default (default is used when no access modifier is provided)
 * non-access modifiers - defines special properties of methods
 *                      - example: static/final/abstract/synchronized/transient/volatile
 * return type          - type of value that is returned by function
 *                      - example: int/double/List<String>/...
 * method name          - name of a method in mixed case with the first letter lowercase, with the first letter of each internal word capitalized
 * method parameters    - type of parameters with their names that are passed to methods (can be empty)
 * method main          - first method that is executed when executing class
 * method overriding    - Java concept that enables you to create multiple implementations of methods with identical name and identical parameters with inheritance (@see {@link org.inheritance.InheritanceExample})
 * method overloading   - Java concept that enables you to create multiple implementations of methods with identical name, but with different method parameters (@see this class)
 */
public class MethodExamples {

    public static void main(String[] args) {
        printLog();
        printDebug("This parameter is passed to function");
        sumOfTwoNumbers(2, 5);            //we pass 2 numbers to a function, but we ignore result

        int sum = sumOfTwoNumbers(5, 10); //we pass 2 numbers to a function, and we save result to variable sum
        System.out.println("Sum of numbers 5 and 10 is: " + sum);

        double sum2 = sumOfTwoNumbers(10.0, 7.5); //example of method overloading - based on parameter type, different function is called
        System.out.println("Sum of numbers 10.0 and 7.5 is: " + sum2);

        //Example of a function with variable count of parameters
        printStrings("First", "Second", "Third", "End");
    }

    /**
     * Function with no return type (void) and no method parameters
     */
    public static void printLog() {
        System.out.println("Printing default log");
        return;      //return will exit function, and in case of void type, it can be omitted
    }

    /**
     * Function with no return type (void) and one method parameter of type String
     * @param details String of details that will be printed out to standard output
     */
    public static void printDebug(String details) {
        System.out.println("Printing log with details: " + details);
    }

    /**
     * Function with return type (int) and two method parameter of type int
     * @param number1 number1
     * @param number2 number2
     * @return sum of numbers passed to function
     */
    public static int sumOfTwoNumbers(int number1, int number2) {
        System.out.println("Method overloading: calling a function with parameters of type int");
        return number1 + number2;  //return will exit function and pass type int to a code that called a function
    }

    /**
     * Function with return type (double) and two method parameter of type double
     * This is an example of method overloading:
     * We have function with identical name, but return value and method parameters are of different type.
     * @param number1 number1
     * @param number2 number2
     * @return sum of numbers passed to function
     */
    public static double sumOfTwoNumbers(double number1, double number2) {
        System.out.println("Method overloading: calling a function with parameters of type double");
        return number1 + number2;  //return will exit function and pass type double to a code that called a function
    }

    /**
     * Function that will print array of strings passed to function.
     * We don't care how many of Strings will be passed to this function.
     * @param stringsArray array of Strings that will be printed to standard output
     */
    public static void printStrings(String... stringsArray) {
        for (String str : stringsArray) {
            System.out.println("printStrings() - printing string: " + str);
        }
    }
}
