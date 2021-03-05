package org.apitesting.payloads;

/**
 * Payloads (data) used in API test.
 * Class contains multiple static methods which returns string of payloads.
 * Static methods are used so that you don't need to create object of this class to access them (see class {@link org.abstraction.StaticClassExample}
 */
public class Payload {
    /**
     * request to ADD place to "fake" google maps hosted at rahulshettyacademy.com with some dummy data
     * @return JSON payload for request body
     */
    public static String getAddPlaceRequest() {
        return "{\r\n" +
                "  \"location\": {\r\n" +
                "    \"lat\": -38.383494,\r\n" +
                "    \"lng\": 33.427362\r\n" +
                "  },\r\n" +
                "  \"accuracy\": 50,\r\n" +
                "  \"name\": \"Stars and bucks coffee shop\",\r\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" +
                "  \"address\": \"29, side layout, cohen 09\",\r\n" +
                "  \"types\": [\r\n" +
                "    \"shoe park\",\r\n" +
                "    \"shop\"\r\n" +
                "  ],\r\n" +
                "  \"website\": \"http://rahulshettyacademy.com\",\r\n" +
                "  \"language\": \"French-IN\"\r\n" +
                "}\r\n" +
                "";
    }

    /**
     * request to UPDATE place to "fake" google maps hosted at rahulshettyacademy.com with some dummy data
     * @param place_id unique id of place that we want to modify
     * @param newAddress new address that we will use to update address
     * @return JSON payload for body
     */
    public static String updatePlaceRequest(String place_id, String newAddress) {
        return "{\r\n" +
                "\"place_id\":\"" + place_id + "\",\r\n" +
                "\"address\":\"" + newAddress + "\",\r\n" +
                "\"key\":\"qaclick123\"\r\n" +
                "}";
    }

    /**
     * request to DELETE place to "fake" google maps hosted at rahulshettyacademy.com with some dummy data
     * @param place_id unique id of place that we want to delete
     * @return JSON payload for body
     */
    public static String deletePlaceRequest(String place_id) {
        return "{\r\n" +
                "\"place_id\":\"" + place_id + "\",\r\n" +
                "\"key\":\"qaclick123\"\r\n" +
                "}";
    }
}
