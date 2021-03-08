package org.apitesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.parsing.Parser;
import org.apitesting.payloads.JSONPayloads;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Deserialization - In Rest Assured context is a process of converting a response body in form of JSON
 *                   with JackSon/Gson/Johnzon framework (or with JAXB framework for XML) into a Java object.
 * Serialization - In Rest Assured context is a process of converting a Java object into Request body (payload)
 *                 in form of JSON with Jackson/Gson/Johnzon framework (or with JAXB framework for XML).
 *
 * How this works is that for JSON (or XML), you create plain old java class (POJO class) representing this JSON (or XML).
 * For each variable or array of JSON (or XML), you create a class variable or array representing these values.
 * If JSON contains nested JSONs (JSON inside a JSON), you create a class within a class.
 * This class structure is demonstrated below, in static classes. (For static classes tutorial, see {@link org.abstraction.StaticClassExample}
 * Classes {@link ClassRepresentingJSON}, {@link ClassRepresentingDashboardJSON} and {@link ClassRepresentingCoursesJSON}
 * represents JSON format available in {@link JSONPayloads#getCoursePriceJSON()}.
 * Take look at that JSON and take a look at those classes, and see how those classes are mapped exactly to match our JSON.
 */
public class Deserialization {

    /**
     * This test represents example of serialization, where you create object representing our JSON, and serialize it to JSON string.
     * This string can be used in RestAssured requests, where you need to fill up body payload.
     */
    @Test
    public void serializationExample() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();      //create Jackson mapper that will handle mapping
        ClassRepresentingJSON jsonObject = new ClassRepresentingJSON(); //create our object representing JSON data
        jsonObject.setName("Serialized object");  //we call function setName() which sets name of simple attribute

        /*
        When you want to fill out dashboard data, you need to create object of nested JSON,
        fill it up with desired data, and then assign it to our object representing whole JSON
         */
        ClassRepresentingDashboardJSON dashboard = new ClassRepresentingDashboardJSON();
        dashboard.setPurchaseAmount(1111);
        dashboard.setWebsite("https://example.com");
        jsonObject.setDashboard(dashboard);

        /*
        When you want to fill out courses data, you need to create a few objects of nested JSON,
        will them up with desired data, create array of those objects,
        and then assign this array to our object representing whole JSON
         */
        ClassRepresentingCoursesJSON course1 = new ClassRepresentingCoursesJSON();
        course1.setTitle("Lonely course");
        course1.setPrice(16850.5);
        course1.setCopies(1);
        ClassRepresentingCoursesJSON course2 = new ClassRepresentingCoursesJSON();
        course2.setTitle("Serialization course");
        course2.setPrice(50.5);
        course2.setCopies(100);
        ClassRepresentingCoursesJSON course3 = new ClassRepresentingCoursesJSON();
        course3.setTitle("Course with empty details - values will be default ones (0, 0.0, null, etc.)");
        List<ClassRepresentingCoursesJSON> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        jsonObject.setCourses(courses);  //assign array of objects to our object representing whole JSON

        String objectAsJSON = objectMapper.writeValueAsString(jsonObject);   //this method will serialize JSON object to JSON string
        System.out.println("JSON object serialized to JSON String: " + objectAsJSON);
    }

    /**
     * This test represents example of deserialization, where you receive JSON string and deserialize it to object mapping this JSON.
     * Alternative way of deserialization example is in method {@link Deserialization#deserializationExample2()}
     */
    @Test
    public void deserializationExample() throws JsonProcessingException {
        //Retrieve JSON string and pretend (just for now) that it is from some API response
        ObjectMapper objectMapper = new ObjectMapper();
        String JSONString = JSONPayloads.getCoursePriceJSON();

        /*
        If we want to deserialize it, we need to create an object representing this class (ClassRepresentingJSON)
        and use an objectMapper where we pass our JSON string from API response, and definition of class that we
        want to map it to (ClassRepresentingJSON.class)
         */
        ClassRepresentingJSON jsonObject = objectMapper.readValue(JSONString, ClassRepresentingJSON.class);

        //Now we can access values from our JSON response by accessing object methods
        System.out.println("jsonObject.getName(): " + jsonObject.getName());
        System.out.println("jsonObject.getDashboard().getPurchaseAmount(): " + jsonObject.getDashboard().getPurchaseAmount());
        System.out.println("jsonObject.getDashboard().getWebsite(): " + jsonObject.getDashboard().getWebsite());
        System.out.println("jsonObject.getCourses().size(): " + jsonObject.getCourses().size());  //courses is array of objects
        System.out.println("jsonObject.getCourses().get(0).getTitle(): " + jsonObject.getCourses().get(0).getTitle());
        System.out.println("jsonObject.getCourses().get(0).getPrice(): " + jsonObject.getCourses().get(0).getPrice());
        System.out.println("jsonObject.getCourses().get(0).getCopies(): " + jsonObject.getCourses().get(0).getCopies());
        System.out.println("jsonObject.getCourses().get(1).getTitle(): " + jsonObject.getCourses().get(1).getTitle());
        System.out.println("jsonObject.getCourses().get(1).getPrice(): " + jsonObject.getCourses().get(1).getPrice());
        System.out.println("jsonObject.getCourses().get(1).getCopies(): " + jsonObject.getCourses().get(1).getCopies());
    }

    /**
     * This test represents another example of deserialization, where you receive JSON string and deserialize it with JsonNode.
     * For this, you don't need class structure of JSON representing your object.
     * Getting values from JsonNode object is done by Jackson framework itself.
     * Alternative way of deserialization example is in method {@link Deserialization#deserializationExample()}
     */
    @Test
    public void deserializationExample2() throws JsonProcessingException {
        //Retrieve JSON string and pretend (just for now) that it is from some API response
        ObjectMapper objectMapper = new ObjectMapper();
        String JSONString = JSONPayloads.getCoursePriceJSON();

        //Parse JSON String into a JsonNode object and used to retrieve data from a specific node
        JsonNode jsonNode = objectMapper.readTree(JSONString);
        System.out.println("jsonNode.get('name').asText(): " + jsonNode.get("name").asText());
        System.out.println("jsonNode.path('dashboard').path('purchaseAmount').asText(): " + jsonNode.path("dashboard").path("purchaseAmount").asText());
        System.out.println("jsonNode.path('dashboard').path('website').asText(): " + jsonNode.path("dashboard").path("website").asText());
        System.out.println("jsonNode.path('courses').size(): " + jsonNode.path("courses").size());
        System.out.println("jsonNode.path('courses').get(0).path('title').asText(): " + jsonNode.path("courses").get(0).path("title").asText());
        System.out.println("jsonNode.path('courses').get(0).path('price').asDouble(): " + jsonNode.path("courses").get(0).path("price").asDouble());
        System.out.println("jsonNode.path('courses').get(0).path('copies').asInt(): " + jsonNode.path("courses").get(0).path("copies").asInt());
        System.out.println("jsonNode.path('courses').get(1).path('title').asText(): " + jsonNode.path("courses").get(1).path("title").asText());
        System.out.println("jsonNode.path('courses').get(1).path('price').asDouble(): " + jsonNode.path("courses").get(1).path("price").asDouble());
        System.out.println("jsonNode.path('courses').get(1).path('copies').asInt(): " + jsonNode.path("courses").get(1).path("copies").asInt());
    }

    /**
     * Test that uses our object representing JSON directly in RestAssured GET query.
     * Note: this is just an example and GET query won't work because we have a dummy url and dummy token, therefore test is ignored
     */
    @Test(enabled = false)
    public void restAssuredExample() {
        ClassRepresentingJSON response = given()
                .queryParam("access_token","TOKEN")
                .expect()
                .defaultParser(Parser.JSON)  //whatever response you get, treat it as a JSON
                .when()
                .get("https://example.com/getResource.php")
                .as(ClassRepresentingJSON.class);  //do HTTP GET and save response to object of class ClassRepresentingJSON

        System.out.println("response.getName(): " + response.getName());
        System.out.println("response.getDashboard().getWebsite(): " + response.getDashboard().getWebsite());
        System.out.println("response.getCourses().size(): " + response.getCourses().size());
        System.out.println("response.getCourses().get(0).getTitle(): " + response.getCourses().get(0).getTitle());
    }

    /**
     * This class is representing JSON available from {@link JSONPayloads#getCoursePriceJSON()}.
     * name is simple variable
     * dashboard is nested JSON (JSON inside JSON) containing 2 variables: purchaseAmount and website
     * courses is array of 4 nested JSONs, each containing 3 variables: title, price, copies.
     * Each attribute contains getters and setters.
     * Setters will be used in serialization, where you set object representing JSON, and convert it to JSON string to do an API request
     * Getters will be used in deserialization, where convert JSON response of API request to an object representing this JSON, and then access its variables
     */
    private static class ClassRepresentingJSON {
        private String name;
        private ClassRepresentingDashboardJSON dashboard;    //dashboard is nested JSON (JSON inside JSON)
        private List<ClassRepresentingCoursesJSON> courses;  //courses is array of nested JSONs

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ClassRepresentingDashboardJSON getDashboard() {
            return dashboard;
        }

        public void setDashboard(ClassRepresentingDashboardJSON dashboard) {
            this.dashboard = dashboard;
        }

        public List<ClassRepresentingCoursesJSON> getCourses() {
            return courses;
        }

        public void setCourses(List<ClassRepresentingCoursesJSON> courses) {
            this.courses = courses;
        }
    }

    /**
     * This class represents nested dashboard JSON containing 2 variables: purchaseAmount and website
     */
    private static class ClassRepresentingDashboardJSON {
        private int purchaseAmount;
        private String website;

        public int getPurchaseAmount() {
            return purchaseAmount;
        }

        public String getWebsite() {
            return website;
        }

        public void setPurchaseAmount(int purchaseAmount) {
            this.purchaseAmount = purchaseAmount;
        }

        public void setWebsite(String website) {
            this.website = website;
        }
    }

    /**
     * This class represents nested course JSON, containing 3 variables: title, price, copies
     */
    private static class ClassRepresentingCoursesJSON {
        private String title;
        private double price;
        private int copies;

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        public int getCopies() {
            return copies;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setCopies(int copies) {
            this.copies = copies;
        }
    }
}
