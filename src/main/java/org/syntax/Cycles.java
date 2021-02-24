package org.syntax;

/**
 * Java cycle will run until condition in cycle is true, e.g. i < 3
 */
public class Cycles {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println("This is FOR cycle that will run 5 times - " + i);
        }

        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String i : cars) {
            System.out.println("This is FOR-EACH loop what will run trough each element of array - " + i);
        }

        int i = 0;
        while (i < 3) {
            System.out.println("This is WHILE loop that will run 3 times - " + i);
            i++;
        }
    }

}
