package org.apitesting;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.apitesting.payloads.JSONPayloads;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * JIRA is the #1 software development tool used by agile teams.
 * It is a proprietary issue tracking product developed by Atlassian that allows bug tracking and agile project management.
 * This class shows simple example of logging into Jira and creating an issue to your project.
 * Pro tip: you can put these test methods to a function and add it to onTestFailure() function to your TestNG listener (see class {@link org.testng.TestNgAdvancedListenerExample}
 *          It will automatically create JIRA issue for each test that will fail :-)
 *
 * For this test to run, you must:
 * 1, Download Jira software from https://www.atlassian.com/software/jira/download
 * 2, Install JIRA on your local computer
 * 3, Register locally with username, password (change JIRA_USERNAME and JIRA_PASSWORD in case you chose different login credentials)
 * 4, Set up local Jira project with specific projectKey (change JIRA_PROJECT_KEY in case you chose different project key)
 * 5, Start JIRA server on your localhost
 * 6, Log in to JIRA from your web browser (http://localhost:8080) with your credentials
 * 7, Create JIRA project and try to access it
 * 8, Enable JIRA attachments in: Settings > System > Attachments > Allow Attachments > YES (needed for test that is attaching a file)
 * 9, Run tests
 * 10, Open Jira board with http://localhost:8080 on your web browser and check if your project contains new issue
 */
@Test
public class JIRAAutomation {

    SessionFilter session;   //this object will be used to pass session ID from login to create issue and other methods

    //Set up JIRA URL, our JIRA credentials and project key that we have created in step 4 of class description
    private final String JIRA_URL = "http://localhost:8080";
    private final String JIRA_PROJECT_KEY = "JIRA";
    private final String JIRA_USERNAME = "username";
    private final String JIRA_PASSWORD = "mySecretPassword123456";

    /**
     * Before running a test, we have to log in to JIRA with our credentials.
     * We will store session identification to SessionFilter with RestAssured.filter() option,
     * and we will use this session in all of our tests, so that we will be authenticated (logged in to JIRA) in tests.
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
    public void createJIRAIssue()
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

    /**
     * Add a comment to JIRA issue with id 10000.
     * Before we add a comment to JIRA, issue must be created, therefore this test method depends on method that will create issue.
     * Use SessionFilter object in request from login, in order to create Jira issue as logged in user.
     */
    @Test(dependsOnMethods = "createJIRAIssue")
    public void addJiraCommentToIssue() {
        final String JIRA_COMMENT = "This is comment that was added automatically from JAVA test";
        final int JIRA_ISSUE_ID = 10000;  //always add a comment to issue with id 10000 -> this should be parametrized

        RestAssured.baseURI= JIRA_URL;

        //Leave a comment in issue
        String response = given()
                .header("Content-Type", "application/json")
                .pathParam("key", JIRA_ISSUE_ID)
                .body(JSONPayloads.getCreateJiraCommentRequest(JIRA_COMMENT)) //obtain JSON request to add a comment to JIRA issue
                .when()
                .filter(session)             //use SessionFilter object that was saved during login. Without this, we wouldn't be authorized
                .post("/rest/api/2/issue/{key}/comment") //send POST with key added in pathParam specifying to which JIRA issue we want to add a comment
                .then()
                .statusCode(201)
                .extract()
                .response().asString();

        System.out.println("JSON response from creating an issue: " + response);

        //Obtain some details from response and assert if jira comment was added successfully
        JsonPath responseJSON = ReusableMethods.stringToJSON(response);
        String issueBody = responseJSON.getString("body");
        assertTrue("KEY of created issue should contain JIRA project key", issueBody.contains(JIRA_COMMENT));
    }

    /**
     * Add an attachment to JIRA issue with id 10000
     * Before we add a comment to JIRA, issue must be created, therefore this test method depends on method that will create issue.
     * File is get from resources folder in the same package structure as testing class (see class {@link org.syntax.ReadFile})
     */
    @Test(dependsOnMethods = "createJIRAIssue")
    public void addJiraAttachmentToIssue() {
        final int JIRA_ISSUE_ID = 10000;  //always add an attachment to issue with id 10000 -> this should be parametrized
        final String ATTACHMENT_NAME = "jira_attachment.txt";

        RestAssured.baseURI= JIRA_URL;

        URL url = this.getClass().getResource(ATTACHMENT_NAME);  //this construction gets resource from resources folder in the same package
        System.out.println("Location of attachment file is: " + url.toString());

        //Add an attachment to Jira issue
        String response = given()
                .header("X-Atlassian-Token", "no-check")
                .header("Content-Type","multipart/form-data")
                .pathParam("key", JIRA_ISSUE_ID)
                .filter(session)             //use SessionFilter object that was saved during login. Without this, we wouldn't be authorized
                .multiPart("file", new File(url.getFile()))  //get file that is fetched from our resources folder
                .when()
                .post("/rest/api/2/issue/{key}/attachments") //send POST with key added in pathParam specifying to which JIRA issue we want to add an attachment
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response().asString();

        System.out.println("JSON response from attaching a file to JIRA issue: " + response);

        //Obtain some details from response and assert if jira attachment was added successfully
        JsonPath responseJSON = ReusableMethods.stringToJSON(response);
        String filename = responseJSON.getString("[0].filename"); //response is array, so we get first object of array and its filename
        assertEquals("Filename of attached file is correct", ATTACHMENT_NAME, filename);
    }
}
