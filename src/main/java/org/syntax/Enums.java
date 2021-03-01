package org.syntax;

/**
 * Enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).
 * This is an example of simple enum.
 * For advanced example of enum, @see class {@link JavaKeywordsEnum}
 */
public enum Enums {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static void main(String... args) {
        Enums currentDay = Enums.SATURDAY;  //assign value of enum

        if (currentDay == Enums.SATURDAY || currentDay == Enums.SUNDAY) {  //compare our value with Enum value
            System.out.println("This is an example of simple enum -> Weekend was selected");
        } else {
            System.out.println("This is an example of simple enum -> Working day was selected");
        }
    }
}
