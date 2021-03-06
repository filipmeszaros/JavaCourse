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
    public static String getUpdatePlaceRequest(String place_id, String newAddress) {
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
    public static String getDeletePlaceRequest(String place_id) {
        return "{\r\n" +
                "\"place_id\":\"" + place_id + "\",\r\n" +
                "\"key\":\"qaclick123\"\r\n" +
                "}";
    }

    /**
     * Returns JSON in String format that we will use in class {@link org.apitesting.JSONParsingExample}
     * @return JSON in String format
     */
    public static String getCoursePriceJSON()
    {
        return "{\r\n" +
                "  \"name\" : \"example JSON\",\r\n" +
                "  \"dashboard\": {\r\n" +
                "    \"purchaseAmount\": 1162,\r\n" +
                "    \"website\": \"rahulshettyacademy.com\"\r\n" +
                "  },\r\n" +
                "  \"courses\": [\r\n" +
                "    {\r\n" +
                "      \"title\": \"Selenium Python\",\r\n" +
                "      \"price\": 49.99,\r\n" +
                "      \"copies\": 6\r\n" +
                "    },\r\n" +
                "    {\r\n" +
                "      \"title\": \"Cypress\",\r\n" +
                "      \"price\": 39.99,\r\n" +
                "      \"copies\": 4\r\n" +
                "    },\r\n" +
                "    {\r\n" +
                "      \"title\": \"RPA\",\r\n" +
                "      \"price\": 44.0,\r\n" +
                "      \"copies\": 10\r\n" +
                "    },\r\n" +
                "     {\r\n" +
                "      \"title\": \"Appium\",\r\n" +
                "      \"price\": 39.5,\r\n" +
                "      \"copies\": 7\r\n" +
                "    }\r\n" +
                "  ]\r\n" +
                "}\r\n";
    }

    /**
     * JSON payload for JIRA request to login
     * @param username  JIRA username
     * @param password  JIRA password
     * @return JSON payload for JIRA request to login
     */
    public static String getLoginToJiraRequest(String username, String password) {
        return "{\r\n" +
                    "\"username\": \"" + username + "\",\r\n" +
                    "\"password\": \"" + password + "\"\r\n" +
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
        return "{\r\n" +
                "   \"fields\": {\r\n" +
                        "\"project\": {\r\n" +
                            "\"key\": \"" + projectKey + "\"\r\n" +
                        "},\r\n" +
                        "\"summary\": \"" + issueSummary + "\",\r\n" +
                        "\"description\": \"" + issueDescription + "\",\r\n" +
                        "\"issuetype\": {\r\n"+
                            "\"name\": \"Bug\"\r\n" +
                        "}\r\n" +
                    "}\r\n" +
                "}";
    }

    /**
     * JSON payload for JIRA request to add a comment to JIRA issue restricted just to Administrators.
     * @param jiraComment comment that will be added to JIRA
     * @return JSON payload for adding a comment to JIRA issue
     */
    public static String getCreateJiraCommentRequest(String jiraComment) {
        return "{\r\n" +
                "   \"body\": \"" + jiraComment + "\",\r\n" +
                "   \"visibility\": {\r\n" +
                "       \"type\": \"role\",\r\n" +
                "       \"value\": \"Administrators\"\r\n" +
                "   }\r\n" +
                "}";
    }
}