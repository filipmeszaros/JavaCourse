package org.apitesting;

import io.restassured.path.json.JsonPath;
import org.apitesting.payloads.JSONPayloads;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

/**
 * In this class, we will demonstrate example of JSON parsing.
 * JSON is common type of response in API testing, so we need to learn how to parse json.
 * JSON used in this test is retrieved from file {@link org.apitesting.payloads.JSONPayloads}
 *
 * Example of JSON:
 * {"menu": {
 *   "id": "file",
 *   "value": "File",
 *   "popup": {
 *     "menuitem": [
 *       {"value": "New", "onclick": "CreateNewDoc()"},
 *       {"value": "Open", "onclick": "OpenDoc()"},
 *     ]
 *   }
 * }}
 *
 */
public class JSONParsingExample {

    final String JSON_STRING = JSONPayloads.getCoursePriceJSON();

    /**
     * This test will demonstrate getting basic values of JSON
     */
    @Test
    public void getBasicValuesOfJSON() {
        //First we will create JsonPath object from JSON string
        JsonPath exampleJSON = ReusableMethods.stringToJSON(JSON_STRING);

        //Then we will access elements of this JsonPath object

        //Variables are accessed like this:
        System.out.println("name: " + exampleJSON.getString("name"));
        System.out.println("dashboard.purchaseAmount: " + exampleJSON.getInt("dashboard.purchaseAmount"));
        System.out.println("dashboard.website: " + exampleJSON.getString("dashboard.website"));

        //Size of arrays (courses: [] is an array in JSON) are retrieved like this:
        System.out.println("course.size(): " + exampleJSON.getInt("courses.size()"));

        //Elements of arrays and their variables are accessed like this:
        //we will get int/String/Double from first course (index 0) by methods getInt(), getString(), getDouble()
        System.out.println("courses[0].title: " + exampleJSON.getString("courses[0].title"));
        System.out.println("courses[0].price: " + exampleJSON.getDouble("courses[0].price"));
        System.out.println("courses[0].copies: " + exampleJSON.getInt("courses[0].copies"));

        //or we can use method get() that will handle parsing of int/String/Double, if we don't know type that we need to parse
        System.out.println("courses[1].title: " + exampleJSON.get("courses[1].title"));
        System.out.println("courses[1].price: " + exampleJSON.get("courses[1].price"));
        System.out.println("courses[1].copies: " + exampleJSON.get("courses[1].copies"));

        //We can even retrieve JSON objects from our JSON in form of HashMap
        Map<String, Object> course4 = exampleJSON.getJsonObject("courses[3]");

        //Now we can retrieve data from that object directly
        System.out.println("Title of 4th course: " + course4.get("title"));
        System.out.println("Price of 4th course: " + course4.get("price"));
        System.out.println("Copies of 4th course: " + course4.get("copies"));
    }

    /**
     * This test will demonstrate getting sum of values from array
     * We will get total sum of all courses sold
     */
    @Test
    public void sumOfCourses() {
        double totalSumOfCourses = 0.0;

        //First we will create JsonPath object from JSON string
        JsonPath exampleJSON = ReusableMethods.stringToJSON(JSON_STRING);
        int numberOfCourses = exampleJSON.getInt("courses.size()");

        //Process each course separately
        for (int i = 0; i < numberOfCourses; i++) {
            //Parse necessary values from each course
            String courseTitle = exampleJSON.getString("courses[" + i + "].title");
            double coursePrice = exampleJSON.getDouble("courses[" + i + "].price");
            int copiesSold = exampleJSON.getInt("courses[" + i +"].copies");

            //Calculate and print total amount for which this course was sold
            double amount = coursePrice * copiesSold;
            System.out.println("Total sum for selling course '" + courseTitle + "' is: " + amount);

            totalSumOfCourses+= amount;
        }
        System.out.println("Total sum for selling all courses: " + totalSumOfCourses);
        assertEquals("Total sum of selling all courses is correct", totalSumOfCourses, 1176.4);
    }
}
