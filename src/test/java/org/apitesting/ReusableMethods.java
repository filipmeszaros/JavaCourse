package org.apitesting;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

    /**
     * Method will return JsonPath object from String JSON response
     * @param response JSON in String format
     * @return JsonPath object
     */
    public static JsonPath stringToJSON(String response)
    {
        return new JsonPath(response);
    }
}
