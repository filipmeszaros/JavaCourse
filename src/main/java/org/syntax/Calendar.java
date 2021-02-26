package org.syntax;


/**
 * Basic examples of working with Date and Calendar
 */
public class Calendar {
    public static void main(String args[]) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        System.out.println("Current year: " + cal.get(java.util.Calendar.YEAR));
        System.out.println("Current month: " + cal.get(java.util.Calendar.MONTH));
        System.out.println("Current day of month: " + cal.get(java.util.Calendar.DAY_OF_MONTH));
        System.out.println("Current day of week: " + cal.get(java.util.Calendar.DAY_OF_WEEK));
        System.out.println("Current day of year: " + cal.get(java.util.Calendar.DAY_OF_YEAR));
        System.out.println("Current week of year: " + cal.get(java.util.Calendar.WEEK_OF_YEAR));
    }
}
