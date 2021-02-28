package org.syntax;

/**
 * Switch statement is used to select one of many code blocks to be executed.
 * The break keyword stops execution of switch block
 * The default keyword specifies some code to run if there is no case match.
 */
public class Switch {
    public static void main(String... args) {
        int day = 5;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break; //if we don't use break, switch will continue
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Wrong number selected (not between 1-7)");
                break;
        }
    }
}
