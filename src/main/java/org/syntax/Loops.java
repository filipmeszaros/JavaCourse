package org.syntax;

/**
 * Java loop will run until condition in cycle is true, e.g. i < 3
 * Break will stop execution of a whole loop
 * Continue will skip execution of loop and continue in other loop
 */
public class Loops {

    public static void main(String[] args) {

        //For loop
        for (int i = 0; i < 5; i++) {
            System.out.println("This is FOR cycle that will run 5 times - " + i);
        }

        //For-each loop
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String i : cars) {
            System.out.println("This is FOR-EACH loop what will run trough each element of array - " + i);
        }

        int i = 0;
        //While loop
        while (i < 3) {
            System.out.println("This is WHILE loop that will run 3 times - " + i);
            i++;
        }

        //Usage of continue
        for (int x = 0; x < 5; x++) {
            if (x==3) {
                System.out.println("CONTINUE: Skipping cycle when x == 3");
                continue;
            }
            System.out.println("CONTINUE: Loop when x == " + x);
        }

        //Use of break
        for (int y = 0; y < 5; y++) {
            if (y==2) {
                System.out.println("BREAK: Exiting cycle when y == 2");
                break;
            }
            System.out.println("BREAK: Loop when y == " + y);
        }
    }

}
