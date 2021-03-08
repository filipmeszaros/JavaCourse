package org.apitesting.payloads;

/**
 * Payloads (data) used in API tests.
 * Class contains multiple static methods which returns string of payloads.
 * Static methods are used so that you don't need to create object of this class to access them (see class {@link org.abstraction.StaticClassExample}
 */
public class JSONPayloads {

    /**
     * request to ADD place to "fake" google maps hosted at rahulshettyacademy.com with some dummy data
     * @return JSON payload for request body
     */
    public static String getAddPlaceRequest() {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Stars and bucks coffee shop\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://rahulshettyacademy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n" +
                "";
    }

    /**
     * request to UPDATE place to "fake" google maps hosted at rahulshettyacademy.com with some dummy data
     * @param place_id unique id of place that we want to modify
     * @param newAddress new address that we will use to update address
     * @return JSON payload for body
     */
    public static String getUpdatePlaceRequest(String place_id, String newAddress) {
        return "{\n" +
                "\"place_id\":\"" + place_id + "\",\n" +
                "\"address\":\"" + newAddress + "\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }

    /**
     * request to DELETE place to "fake" google maps hosted at rahulshettyacademy.com with some dummy data
     * @param place_id unique id of place that we want to delete
     * @return JSON payload for body
     */
    public static String getDeletePlaceRequest(String place_id) {
        return "{\n" +
                "\"place_id\":\"" + place_id + "\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }

    /**
     * Returns JSON in String format that we will use in classes {@link org.apitesting.JSONParsingExample} and {@link org.apitesting.DeserializationAndSerialization}
     * @return JSON in String format
     */
    public static String getCoursePriceJSON()
    {
        return "{\n" +
                "  \"name\" : \"example JSON\",\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 1162,\n" +
                "    \"website\": \"rahulshettyacademy.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 49.99,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 39.99,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 44.0,\n" +
                "      \"copies\": 10\n" +
                "    },\n" +
                "     {\n" +
                "      \"title\": \"Appium\",\n" +
                "      \"price\": 39.5,\n" +
                "      \"copies\": 7\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

    /**
     * JSON payload for JIRA request to login
     * @param username  JIRA username
     * @param password  JIRA password
     * @return JSON payload for JIRA request to login
     */
    public static String getLoginToJiraRequest(String username, String password) {
        return "{\n" +
                    "\"username\": \"" + username + "\",\n" +
                    "\"password\": \"" + password + "\"\n" +
                "}";
    }

    /**
     * JSON payload for JIRA request to create JIRA issue.
     * Created from: https://developer.atlassian.com/server/jira/platform/jira-rest-api-examples/
     * @param projectKey  project key for JIRA
     * @param issueSummary Summary of issue
     * @param issueDescription Description of issue
     * @return JSON payload for JIRA create issue request
     */
    public static String getCreateJiraIssueRequest(String projectKey, String issueSummary, String issueDescription) {
        return "{\n" +
                "   \"fields\": {\n" +
                        "\"project\": {\n" +
                            "\"key\": \"" + projectKey + "\"\n" +
                        "},\n" +
                        "\"summary\": \"" + issueSummary + "\",\n" +
                        "\"description\": \"" + issueDescription + "\",\n" +
                        "\"issuetype\": {\n"+
                            "\"name\": \"Bug\"\n" +
                        "}\n" +
                    "}\n" +
                "}";
    }

    /**
     * JSON payload for JIRA request to add a comment to JIRA issue restricted just to Administrators.
     * @param jiraComment comment that will be added to JIRA
     * @return JSON payload for adding a comment to JIRA issue
     */
    public static String getCreateJiraCommentRequest(String jiraComment) {
        return "{\n" +
                "   \"body\": \"" + jiraComment + "\",\n" +
                "   \"visibility\": {\n" +
                "       \"type\": \"role\",\n" +
                "       \"value\": \"Administrators\"\n" +
                "   }\n" +
                "}";
    }
}
