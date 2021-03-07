package org.syntax;

/**
 * Recursion is the technique of making a function call itself.
 * This programming technique provides a way to break complicated problems down into simple problems which are easier to solve.
 */
public class Recursion {
    public static void main(String[] args) {
        helloWorld(5);

        System.out.println("Fibonacci(0): " + fibonacci(0));
        System.out.println("Fibonacci(1): " + fibonacci(1));
        System.out.println("Fibonacci(2): " + fibonacci(2));
        System.out.println("Fibonacci(3): " + fibonacci(3));
        System.out.println("Fibonacci(4): " + fibonacci(4));
        System.out.println("Fibonacci(5): " + fibonacci(5));
        System.out.println("Fibonacci(6): " + fibonacci(6));
        System.out.println("Fibonacci(7): " + fibonacci(7));

        System.out.println("Factorial(0): " + factorial(0));
        System.out.println("Factorial(1): " + factorial(1));
        System.out.println("Factorial(2): " + factorial(2));
        System.out.println("Factorial(3): " + factorial(3));
        System.out.println("Factorial(4): " + factorial(4));
        System.out.println("Factorial(5): " + factorial(5));
    }

    /**
     * Function will print Hello World x number of times
     * @param x how many times "Hello World!" should be printed out
     */
    public static void helloWorld(int x) {
        if (x < 1)
            return;      //stop execution when we shouldn't print Hello world anymore

        System.out.println("Hello World!");   //print Hello world
        helloWorld(x - 1);                 //call function again (now one time less) to print remaining occurences of Hello world
    }

    /**
     * Function will return value of fibonacci sequence in given index
     * Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21
     * fibonacci(0) = 0
     * fibonacci(1) = 1
     * fibonacci(7) = 13
     * @param n fibonacci number we want to calculate
     * @return fibonacci number of param n
     */
    public static int fibonacci(int n) {
        if (n == 0)
            return 0;  //return result for fibonacci(0)
        if (n == 1)
            return 1;  //return result for fibonacci(1)

        return (fibonacci(n - 1) + fibonacci(n - 2));   //else, return sum of previous 2 numbers
    }

    /**
     * Function to return factorial
     * factorial(5) = 5*4*3*2*1
     * factorial(4) = 4*3*2*1
     * factorial(1) = 1
     * factorial(0) = 1
     * @param n number of factorial(n) we want to calculate
     * @return factorial of n
     */
    public static int factorial(int n) {
        if (n == 0 || n == 1)
            return 1;   //return result for factorial(0) and factorial(1)

        return n * factorial(n - 1);  //else, return current number multiplied by factorial of lesser number
    }
}
