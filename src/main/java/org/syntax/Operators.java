package org.syntax;

/**
 * Operators are used to perform operations on variables and values.
 */
public class Operators {
    public static void main(String[] args) {
        arithmeticOperators();
        assignmentOperators();
        comparisonOperators();
        logicalOperators();
        ternaryOperator();
    }

    public static void arithmeticOperators() {
        System.out.println("--- Arithmetic operators ---");
        int x = 5;
        int y = 3;

        System.out.println(x + y); //Addition
        System.out.println(x - y); //Subtraction
        System.out.println(x * y); //Multiplication
        System.out.println(x / y); //Division (divides one value by another)
        System.out.println(x % y); //Modulus (returns the division remainder)
        System.out.println(x++); //Increment (Increases the value of a variable by 1)
        System.out.println(x--); //Decrement (Decreases the value of a variable by 1)
    }

    public static void assignmentOperators() {
        System.out.println("--- Assignment operators ---");

        int x = 1;              //x = 1
        System.out.println(x);  //prints 1
        x+= 10;                 //x = x + 10
        System.out.println(x);  //prints 11
        x-= 2;                  //x = x - 2
        System.out.println(x);  //prints 9
        x*=3;                   //x = x * 3
        System.out.println(x);  //prints 27
        x/=9;                   //x = x / 9
        System.out.println(x);  //prints 3
        x%=2;                   //x = x % 2;
        System.out.println(x);  //prints 1
        x<<=2;                  //rotate bits by 2 positions to the left, e.g. 0001 to 0100
        System.out.println(x);  //prints 4
        x>>=1;                  //rotate bits by 1 positions to the right, e.g. 0100 to 0010
        System.out.println(x);  //prints 2
    }

    public static void comparisonOperators() {
        System.out.println("--- Comparison operators ---");
        int x = 5;
        int y = 10;
        if (x == y)
            System.out.println(x + " equal to " + y);
        if (x != y)
            System.out.println(x + " not equal to " + y);
        if (x > y)
            System.out.println(x + " greater than " + y);
        if (x < y)
            System.out.println(x + " less than " + y);
        if (x >= y)
            System.out.println(x + " greater than or equal to " + y);
        if (x <= y)
            System.out.println(x + " less than or equal to " + y);
    }

    public static void logicalOperators() {
        System.out.println("--- Logical operators ---");
        int x = 5;
        int y = 10;

        if (x > 1 && y > 1) //Logical AND
            System.out.println("Returns true if both statements are true");

        if (x > 1 || y > 1) //Logical OR
            System.out.println("Returns true if one of statements are true");

        if (!(x > 1 && y > 1)) //Logical AND
            System.out.println("Reverse the result, returns false if the result is true");
    }

    /**
     * Java ternary operator function like a simplified Java IF statement.
     * Ternary operator consists of a condition that evaluates to either true or false, plus a value that is returned in case of one or another evaluation.
     * booleanExpression ? expression1 : expression2
     * expression1 is returned in case booleanExpression is true
     * expression2 is returned in case booleanExpression is false
     */
    public static void ternaryOperator() {
        System.out.println("--- Ternary operator ---");

        //Example 1
        int num = 8;
        String msg = "";
        if(num > 10) {
            msg = "Number is greater than 10";
        } else {
            msg = "Number is less than or equal to 10";
        }
        System.out.println(msg);

        //Identical evaluation as in Example 1
        final String msg2 = num > 10
                ? "Number is greater than 10"
                : "Number is less than or equal to 10";
        System.out.println(msg2);

        //Example of usage: if we want to assign value only from object that is not null
        //String value = object != null ? object.getValue() : null;
    }
}
