package org.streams;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Some Collections that we will use for learning purposes of Java Streams
 */
public class CollectionDefinitions {
    public static ArrayList<String> names = new ArrayList<>();
    public static HashSet<String> months = new HashSet<>();
    public static HashSet<Integer> integers = new HashSet<>();
    public static ArrayList<String> days = new ArrayList<>();
    public static HashSet<String> emails = new HashSet<>();

    public CollectionDefinitions() {
        names.add("John");
        names.add("Don");
        names.add("Filip");
        names.add("Adam");
        names.add("Marcel");
        names.add("Xena");
        names.add("Lucia");
        names.add("Lucie");
        names.add("Marek");
        names.add("Andromeda");
        names.add("Andrej");
        names.add("Martin");
        names.add("Jozo");
        names.add("Zolo");
        names.add("Michal");
        names.add("Andrej");
        names.add("Laco");
        names.add("Olga");
        names.add("Betty");
        names.add("Anastasia");

        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        integers.add(1);
        integers.add(5);
        integers.add(0);
        integers.add(-1);
        integers.add(-321);
        integers.add(1024);
        integers.add(768);
        integers.add(222);
        integers.add(10000);
        integers.add(7);
        integers.add(32);
        integers.add(4);
        integers.add(-30);
        integers.add(-31);
        integers.add(-500);
        integers.add(-1234);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(0);
        integers.add(0);
        integers.add(-500);
        integers.add(-500);

        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        emails.add("email@gmail.com");
        emails.add("john@doe.com");
        emails.add("cz@seznam.cz");
    }
}
