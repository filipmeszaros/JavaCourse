package org.syntax;

/**
 * Variables are used to store simple data in program
 */
public class Variables {

    public static void main(String[] args) {
        String str1 = "John";
        String str2 = new String("Doe");
        int i = 10;
        float f = 10.5f;
        long l = 6690l;
        boolean b = true;
        char ch = 'b';

        final String STR_CONSTANT = "STRING CONSTANT THAT CANNOT BE CHANGED";

        System.out.println("String is used for strings, such as: " + str1 + " and " + str2);
        System.out.println("Integer is used for numbers, such as: " + i);
        System.out.println("Boolean is used for true/false values, such as: " + b);
        System.out.println("Char is used for single characters, such as: " + ch);
        System.out.println("This String variable cannot be changed: " + STR_CONSTANT);
    }
}
