package org.apitesting;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.apitesting.payloads.JSONPayloads;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertTrue;

/**
 * JIRA is the #1 software development tool used by agile teams.
 * It is a proprietary issue tracking product developed by Atlassian that allows bug tracking and agile project management.
 *
 * For this test to run, you must:
 * 1, Download Jira software from https://www.atlassian.com/software/jira/download
 * 2, Install it on your localhost
 * 3, Register locally with username, password (change JIRA_USERNAME and JIRA_PASSWORD in case you chose different login credentials)
 * 4, Set up local Jira project with specific projectKey (change JIRA_PROJECT_KEY in case you chose different project key)
 * 5, Run test
 * 6, Open Jira with http://localhost:8080 on your web browser and check project details of created issue, etc.
 */
public class JIRAAutomation {

    SessionFilter session;   //this object will be used to pass session ID from login to create issue and other methods

    //Set up JIRA URL, our JIRA credentials and project key that we have created in step 4 of class description
    private final String JIRA_URL = "http://localhost:8080";
    private final String JIRA_PROJECT_KEY = "JIRA";
    private final String JIRA_USERNAME = "username";
    private final String JIRA_PASSWORD = "mySecretPassword123456";

    /**
     * Before running a test, we have to log in to JIRA with our credentials.
     */
    @BeforeTest
    public void loginToJira() {
        this.session = new SessionFilter();
        RestAssured.baseURI= JIRA_URL;

        String loginResponse = given()
                .header("Content-Type", "application/json")
                .body(JSONPayloads.getLoginToJiraRequest(JIRA_USERNAME,JIRA_PASSWORD))  //obtain JSON for login request with our credentials
                .log().all()
                .filter(session)          //very important: save our current session to SessionFilter. It will save session for other tests
                .when()
                .post("/rest/auth/1/session")
                .then()
                .extract()
                .response().asString();

        //Obtain some details from response and assert if we logged to JIRA successfully
        System.out.println("JSON response from login to JIRA: " + loginResponse);
        assertTrue("Response from login should contain JSESSIONID", loginResponse.contains("JSESSIONID"));
    }

    /**
     * Create new issue to our own JIRA project, with our own credentials, and with our own issue details
     * Use SessionFilter object in request from login, in order to create Jira issue as logged in user.
     */
    @Test
    public void JiraAPICreateIssue()
    {
        final String ISSUE_SUMMARY = "Unable to log in with correct credentials";
        final String ISSUE_DESCRIPTION = "When I log in with correct credentials, it throws error 404";

        //Creating Issue/Defect
        RestAssured.baseURI= JIRA_URL;
        String response = given()
                .header("Content-Type", "application/json")
                .body(JSONPayloads.getCreateJiraIssueRequest(JIRA_PROJECT_KEY, ISSUE_SUMMARY, ISSUE_DESCRIPTION)) //obtain JSON request to create JIRA issue
                .when()
                .filter(session)             //use SessionFilter object that was saved during login. Without this, we wouldn't be authorized
                .post("/rest/api/2/issue")
                .then()
                .statusCode(201)
                .extract()
                .response().asString();

        System.out.println("JSON response from creating an issue: " + response);

        //Obtain some details from response and assert if jira issue was created correctly
        JsonPath responseJSON = ReusableMethods.stringToJSON(response);
        int issueId = responseJSON.getInt("id");
        String issueKey = responseJSON.getString("key");
        assertTrue("ID of created issue should be higher than 10000", issueId >= 10000);
        assertTrue("KEY of created issue should contain JIRA project key", issueKey.contains(JIRA_PROJECT_KEY));
    }
}
