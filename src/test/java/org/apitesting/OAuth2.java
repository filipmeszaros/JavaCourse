package org.apitesting;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.selenium.SeleniumConfiguration;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

/**
 * OAuth 2.0 is authentication protocol, which supports multiple types of authentication.
 * It is widely used in applications that enables you to log in via Facebook or via Gmail.
 * This class demonstrates automation of logging into www.locktrip.com (portal with cheapest hotels worldwide)
 * with google credentials (authorization is provided by google itself).
 *
 * Authorization types:
 *  - authorization code (when you have a code that proves that you are authorized)
 *  - client credentials (when you use your login credentials)
 *  - password (password of resource owner)
 *
 *  OAuth2.0 flow of our example of logging into www.locktrip.com with our google credentials (authorization code) used in this class:
 *  1, We open webpage www.locktrip.com, and login window will directly appear
 *  2, With selenium, we click on "Enter with Google", which opens another window for OAuth2.0 authorization via google
 *  3, We log in with Google username and credentials, and we receive code="" in new URL
 *     clientID + username + password -> code
 *  4, We save this url and parse code="" attribute from it
 *  5, LockTrip then sends POST request to Google with its clientId, clientSecret and other parameters (that you should get from developer) along with retrieved code from step 4
 *     code + clientID + clientSecret -> access_token
 *  6, Google sends LockTrip access_token parameter
 *  7, This access_token parameter is used in all other request, so that we will stay authenticated in LockTrip
 *
 * Note: this test will fail because OAuth2.0 contract changed in LockTrip application since this test was written.
 * OAuth2.0 tutorials: https://medium.com/tech-learn-share/getting-access-token-in-oauth2-0-using-rest-assured-225593b8fc1a
 *                     https://devqa.io/rest-assured-oauth2-workflow-examples/
 */
public class OAuth2 extends SeleniumConfiguration {

    private String code;
    private String access_token;

    //Actual google credentials
    private final String GMAIL_USERNAME = "JavaCourseDummyEmail@gmail.com";
    private final String GMAIL_PASSWORD = "DummyPasswd123";

    //These are dummy data that should be retrieved by developer who programmed OAuth2.0 authentication of application you are testing
    private final String REDIRECT_URI = "https://locktrip.com/getHotel.php";
    private final String CLIENT_ID = "random-string.apps.googleusercontent.com";
    private final String CLIENT_SECRET = "secretCodeOfLockTrip";

    /**
     * This test demonstrates steps 1-4 of class description.
     * @throws InterruptedException for purposes of Thread.sleep()
     */
    @Test(dependsOnMethods = "getCoursesWithoutAuthorization")
    public void getOAuthCode() throws InterruptedException {
        driver.get("https://locktrip.com/");
        driver.findElement(By.xpath("//*[contains(text(), 'Enter with Google')]")).click();
        Thread.sleep(1000);

        //After clicking on "Enter with Google", second window with Google log in will appear
        Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String lockTripWindowId = it.next();   //first window is default one - LockTrip window
        String oAuthWindowId = it.next();    //second window is new window - Google OAuth2.0 window

        //Save window URLs, close them and assert them.
        driver.switchTo().window(lockTripWindowId);
        String lockTripWindowURL = driver.getCurrentUrl();
        driver.switchTo().window(oAuthWindowId);
        String oAuthWindowURL = driver.getCurrentUrl();

        System.out.println("Parent window URL: " + lockTripWindowURL);
        System.out.println("Child window URL: " + oAuthWindowURL);
        assertTrue("Parent window URL is correct", lockTripWindowURL.contains("locktrip.com"));
        assertTrue("Child window URL is correct", oAuthWindowURL.contains("accounts.google.com"));

        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(GMAIL_USERNAME);
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(GMAIL_PASSWORD);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
        String urlAfterLogin = driver.getCurrentUrl();
        System.out.println("URL after log in: " + urlAfterLogin);
        assertTrue("URL of windows are not the same", !urlAfterLogin.equals(oAuthWindowURL));

        Thread.sleep(5000);
        driver.switchTo().window(lockTripWindowId);
        String lockTripURL = driver.getCurrentUrl();
        assertTrue("LockTrip URL is correct", lockTripURL.contains("locktrip.com"));
        String pageSource = driver.getPageSource();
        assertTrue("Page source contain our e-mail (we are logged in): ", pageSource.contains(GMAIL_USERNAME.toLowerCase()));

        String partialCode = lockTripURL.split("code=")[1]; //skip everything before code=
        code = partialCode.split("&")[0];  //skip everything after code parameter ends
        System.out.println("Code retrieved from URL: " + code);
        //Our current url should contain code="" that we need to use in second request
    }

    /**
     * This test demonstrates steps 5-6 of class description.
     */
    @Test(dependsOnMethods = "getOAuthCode")
    public void getOAuthAuthorizationToken() {
        String response = given()
                .urlEncodingEnabled(false)
                .queryParam("code", code) //use code retrieved from previous test
                .queryParam("client_id",CLIENT_ID)         //use data provided by OAuth2.0 contract
                .queryParam("client_secret",CLIENT_SECRET) //use data provided by OAuth2.0 contract
                .queryParam("redirect_uri",REDIRECT_URI)   //use data provided by OAuth2.0 contract
                .queryParam("grant_type","authorization_code")
                .when()
                .log().all()
                .post("https://www.googleapis.com/oauth2/v4/token")
                .then()
                .assertThat()
                .extract()
                .response().asString();

        System.out.println("Response of POST to retrieve access_token: " + response);

        //When we want to parse JSON response, we can use JsonPath
        //We will get unique access_token from POST request
        JsonPath js = new JsonPath(response);
        access_token = js.getString("access_token");  //get access_token parameter from JSON response
        System.out.println("access_token from JSON response is: " + access_token);
    }

    /**
     * This test demonstrates step 7 of class description.
     */
    @Test(dependsOnMethods = "getOAuthAuthorizationToken")
    public void getLockTripRequest() {
        String response = given()
                .queryParam("access_token", access_token)  //use access token retrieved in previous step
                .when()
                .log().all()
                .get("https://locktrip.com.com/getRecommendedHotelForMe.php")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        System.out.println("Response of GET is: " + response);
    }

    /**
     * This step demonstrates try to access a resource without authentication token
     */
    @Test
    public void getCoursesWithoutAuthorization() {
        String response = given()
                .queryParam("access_token", "")
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().asString();

        System.out.println("Response of GET is: " + response);
        assertTrue("Response of GET is - AUTHENTICATION FAILED", response.contains("AUTHENTICATION FAILED"));
        assertTrue("Response of GET is - PLEASE ENTER VALID ACCESS TOKEN", response.contains("PLEASE ENTER VALID ACCESS TOKEN"));
    }
}
