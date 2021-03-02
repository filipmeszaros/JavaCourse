package org.syntax;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Basic examples of working with Dates and times.
 * Date              - represents both date and time
 * LocalDate         - represents a date (year, month, day)
 * LocalTime         - represents a time (hour, minute, second, nanosecond)
 * LocalDateTime     - represents both date and time
 * DateTimeFormatter - formatter for displaying and parsing date-time objects
 */
public class DateAndTime {
    public static void main(String args[]) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        System.out.println("Date '" + d.toString() + "' converted to  another  format: '" + sdf.format(d) + "'");
        System.out.println("Date '" + d.toString() + "' converted to different format: '" + sdf2.format(d) + "'");

        System.out.println("Displaying current date with LocalDate.now(): " + LocalDate.now());
        System.out.println("Displaying current time with LocalTime.now(): " + LocalTime.now());
        System.out.println("Displaying current date&time with LocalDateTime.now(): " + LocalDateTime.now());

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter myFormatObj3 = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        DateTimeFormatter myFormatObj4 = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        System.out.println("Displaying current date&time in format1: " + currentDateTime.format(myFormatObj1));
        System.out.println("Displaying current date&time in format2: " + currentDateTime.format(myFormatObj2));
        System.out.println("Displaying current date&time in format3: " + currentDateTime.format(myFormatObj3));
        System.out.println("Displaying current date&time in format4: " + currentDateTime.format(myFormatObj4));
    }
}
