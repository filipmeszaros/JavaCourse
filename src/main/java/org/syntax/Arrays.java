package org.syntax;

/**
 * Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value.
 */
public class Arrays {
    public static void main(String[] args) {
        System.out.println("----- Single dimensional arrays -----");
        singleDimensionalArrays();
        System.out.println("----- Multi dimensional arrays -----");
        multiDimensionalArrays();
        System.out.println("----- Array of custom classes -----");
        arrayOfCustomClasses();
    }

    public static void singleDimensionalArrays() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        int[] numbers = {10, 20, 30, 40};
        String[] colors = new String[3];  //declares array of 3 Strings
        colors[0] = "Red";      //defines first value in array
        colors[1] = "Blue";
        colors[2] = "Green";
        //colors[3] = "Black";  //This will throw an Exception because we have only 3 elements in array

        System.out.println("First item of array cars is cars[0]: " + cars[0]);
        System.out.println("Last item of array numbers is: " + numbers[numbers.length - 1]);

        for (int i = 0; i < cars.length; i++) {
            System.out.println("Iterating through array with FOR loop: " + cars[i]);
        }

        for (String car : cars) {
            System.out.println("Iterating through array with FOR_EACH loop: " + car);
        }
    }

    /**
     * Arrays can have multiple dimensions. They can even have 3, 4 or more dimensions
     */
    public static void multiDimensionalArrays() {
        // ^ y (second index of matrix)
        // |
        // |  9  0 -1 -2
        // |  5  6  7  8
        // |  1  2  3  4
        // |---------------> x (first index of matrix)
        int[][] matrix4x3 = { {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 0, -1, -2}};
        System.out.println("First item of matrix: " + matrix4x3[0][0]);
        System.out.println("Last item of matrix: " + matrix4x3[2][3]);
    }

    /**
     * Array can consist of primitive values (int, String, ...), but even from complex objects, like in this example
     */
    public static void arrayOfCustomClasses() {
        InnerClass[] customArray = { new InnerClass(1, "first"), new InnerClass(2, "second"), new InnerClass(3, "third") };
        for (int i = 0; i < customArray.length; i++) {
            System.out.println("Array of custom objects (index " + i + ") has values: " + customArray[i].number + " and " + customArray[i].string);
        }
    }

    /**
     * You can create class inside a class. For more details @see class {@link org.abstraction.StaticClassExample}
     */
    static class InnerClass {
        int number;
        String string;

        //Constructor is called each time object is created
        InnerClass(int num, String str) {
            this.number = num;
            this.string = str;
        }
    }
}
