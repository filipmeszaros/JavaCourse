package org.mutationtesting;

/**
 * This class demonstrates application that will be tested with mutation testing principle.
 * Class contains 3 different methods, that are being tested by class src/test/java/org/mutationtesting/MutationTestingAppTest.java
 * See mentioned class for more details
 */
public class MutationTestingApp {

    /**
     * Returns true in case number is positive or 0
     * @param n number
     * @return true in case number is positive
     */
    public boolean isPositive(int n) {
        boolean result = false;
        if (n >= 0) {
            result = true;
        }
        return result;
    }

    /**
     * Returns true in case number is prime number (divisible only by yourself)
     * @param n number
     * @return true in case number is prime
     */
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if string is palindrome (identical when reversed)
     * @param input string to check
     * @return true in case input is palindrome
     */
    public boolean isPalindrome(String input) {
        if (input.length() == 0 || input.length() == 1) {
            return true;
        }

        char firstChar = input.charAt(0);
        char lastChar  = input.charAt(input.length() - 1);
        String mid = input.substring(1, input.length() - 1);

        return (firstChar == lastChar) && isPalindrome(mid);
    }
}
