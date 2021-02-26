package org.syntax;

import java.text.SimpleDateFormat;

/**
 * Basic examples of working with Date
 */
public class Date {
    public static void main(String args[]) {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        System.out.println("Date '" + d.toString() + "' converted to  another  format: '" + sdf.format(d) + "'");
        System.out.println("Date '" + d.toString() + "' converted to different format: '" + sdf2.format(d) + "'");
    }
}
