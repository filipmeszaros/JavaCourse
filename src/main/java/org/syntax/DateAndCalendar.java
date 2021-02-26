package org.syntax;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Basic examples of working with Date and Calendar
 */
public class DateAndCalendar {
    public static void main(String args[]) {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        System.out.println("Date '" + d.toString() + "' converted to  another  format: '" + sdf.format(d) + "'");
        System.out.println("Date '" + d.toString() + "' converted to different format: '" + sdf2.format(d) + "'");

        Calendar cal = Calendar.getInstance();
        System.out.println("Current year: " + cal.get(Calendar.YEAR));
        System.out.println("Current month: " + cal.get(Calendar.MONTH));
        System.out.println("Current day of month: " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("Current day of week: " + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("Current day of year: " + cal.get(Calendar.DAY_OF_YEAR));
        System.out.println("Current week of year: " + cal.get(Calendar.WEEK_OF_YEAR));
    }
}
