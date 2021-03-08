package org.apitesting;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apitesting.payloads.JSONPayloads;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * API - Application Programming Interface.
 * This class contains basic API tests for demonstrating purposes of API testing.
 * Class is using TestNG framework (see package {@link org.testng}), so each test can be run separately.
 * Each test method depends on another test method, so that we will use this order: CREATE, UPDATE, GET, DELETE.
 * Otherwise, because TestNG is running methods in random order, we could get intermittent failures (if we would for example like to GET place that was already DELETED)
 * Last methods demonstrates concept of RequestSpecBuilder - utility that is used to construct a API request specifications.
 *                                 and ResponseSpecBuilder - utility that is used to verify an API responses.
 *
 * Terminology:
 * Endpoint        - address when API is hosted on server
 * CRUD operations - Create, Retrieve, Update, Delete (HTTP methods using these operations are POST, GET, PUT, DELETE)
 * URI             - {Base URL}/{Resource}/{Path parameter}?{Query parameter 1}&{Query parameter 2}
 * Base URL        - URL where API is available (e.g. google.com)
 * Resource        - represents API/Collection which cam be retrieved from server (e.g. google.com/images, google.com/maps)
 * Path parameter  - purpose of path parameter is to specify source of collection (e.g. google.com/maps/brno)
 * Query parameter - are to sort or filter results. (e.g. google.com/images?sort_by=creation_date
 * Headers/Cookies - represents additional details mapped to api request and response
 * API request     - is send to server when you want to retrieve data from it
 * API response    - is send from server to you, after you retrieved some data
 * Postman         - UI software that enables you to send/receive API requests, change headers/cookies, run tests, etc.
 * RestAssured     - Java framework for API testing (www.rest-assured.io)
 *
 * RestAssured - each test contains 3 parts
 * given() - all input details
 * when()  - send of request
 * then()  - validation of response
 */
public class BasicAPITest {

    final private String BASE_URI = "https://rahulshettyacademy.com";
    private String place_id;  //unique identifier of our place in "fake" google maps

    /**
     * Before tests, set up RestAssured URI to which we will send all requests
     */
    @BeforeTest
    public void setupBaseURI() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test will submit POST request with adding new place to "fake" google maps hosted in rahulshettyacademy.com.
     * Test will save response and get place_id from this response, which is uniquely identifying this new place.
     */
    @Test
    public void exampleOfPOSTRequest() {
        String response = given()                            //given() marks all input details
                .log().all()                                 //log all
                .queryParam("key", "qaclick123")  //set query parameter that will allow us to use this API
                .header("Content-Type", "application/json")    //set header
                .body(JSONPayloads.getAddPlaceRequest())    //set body payload
                .when()                                     //when() marks sending of request
                .post("maps/api/place/add/json")         //run POST to a given endpoint
                .then()                                     //then() marks validation of response
                .assertThat()                               //assertThat() marks that we are going to use asserts
                .statusCode(200)                            //assert that status code is 200 (POST was successful)
                .contentType("application/json")           //assert that content type is application/json
                .body("scope", equalTo("APP"))    //assert that attribute "scope" of JSON body contains "APP"
                .header("server", "Apache/2.4.18 (Ubuntu)")  //assert header
                .extract()                 //extract is used when you want to get something from response
                .response().asString();    //extract response as string, and save it to variable response (see fist line)

        System.out.println("JSON Response of POST request is: " + response);

        //When we want to parse JSON response, we can use JsonPath
        //We will get unique place id from POST request
        JsonPath js = new JsonPath(response);
        this.place_id = js.getString("place_id");  //from object js containing response, get string place_id

        System.out.println("Place id from POST response is: " + this.place_id);
    }


    /**
     * Test will submit PUT request that will update previously created place on "fake" google maps hosted in rahulshettyacademy.com.
     * Test will use unique place_id that is identifying our place that was added to "fake" maps by POST request in previous test.
     */
    @Test(dependsOnMethods = "exampleOfPOSTRequest") //before we want to UPDATE address, we must create it with POST
    public void exampleOfPUTRequest() {
        String response = given()                                  //given() marks all input details
                .log().all()                                       //log all
                .queryParam("key", "qaclick123")        //set query parameter that will allow us to use this API
                .header("Content-Type", "application/json")  //set header
                .body(JSONPayloads.getUpdatePlaceRequest(this.place_id, "UPDATED ADDRESS 123"))  //set body payload with updated address
                .when()                                           //when() marks sending of request
                .put("maps/api/place/update/json")             //run PUT to a given endpoint
                .then()                                          //then() marks validation of response
                .assertThat()                                    //assertThat() marks that we are going to use asserts
                .log().all()                                     //log all asserts
                .statusCode(200)                                 //assert that status code is 200 (PUT was successful)
                .contentType("application/json")           //assert that content type is application/json
                .body("msg", equalTo("Address successfully updated")) //assert that attribute "msg" of JSON body contains mentioned text
                .extract()                 //extract is used when you want to get something from response
                .response().asString();    //extract response as string, and save it to variable response (see fist line)

        System.out.println("JSON Response of PUT request is: " + response);
    }

    /**
     * Test will submit GET request that will get previously created place on "fake" google maps hosted in rahulshettyacademy.com.
     * Test will use unique place_id that is identifying our place that was added to "fake" maps by POST request in previous test.
     */
    @Test(dependsOnMethods = { "exampleOfPOSTRequest", "exampleOfPUTRequest" }) //before we want to GET address, we must create it with POST (optional: we will also update it)
    public void exampleOfGETRequest() {
        String response = given()                           //given() marks all input details
                .log().all()                                //log all
                .header("Content-Type", "application/json")  //set header
                .queryParam("key", "qaclick123")  //set query parameter that will allow us to use this API
                .queryParam("place_id", this.place_id)    //set query parameter that will specify which place we want to GET
                .when()                                     //when() marks sending of request
                .get("maps/api/place/get/json")          //run GET to a given endpoint
                .then()                                    //then() marks validation of response
                .assertThat()                              //assertThat() marks that we are going to use asserts
                .log().all()                               //log all
                .statusCode(200)                           //assert that status code is 200 (PUT was successful)
                .contentType("application/json")           //assert that content type is application/json
                .extract()                                 //extract is used when you want to get something from response
                .response().asString();                    //extract response as string, and save it to variable response (see fist line)

        System.out.println("JSON response of GET request is: " + response);

        //When we want to parse JSON response, we can use JsonPath
        //We will get name from GET request
        JsonPath js = new JsonPath(response);
        String placeName = js.getString("name");
        System.out.println("Name of place: " + placeName);
        assertEquals("Name of place is correct", "Stars and bucks coffee shop", placeName);
    }

    /**
     * Test will submit DELETE request that will delete previously created place on "fake" google maps hosted in rahulshettyacademy.com.
     * Test will use unique place_id that is identifying our place that was added to "fake" maps by POST request in previous test.
     */
    @Test(dependsOnMethods = "exampleOfGETRequest") //before we want to DELETE address, we will GET it first
    public void exampleOfDELETERequest() {
        String response = given()                           //given() marks all input details
                .log().all()                                //log all
                .header("Content-Type", "application/json")  //set header
                .queryParam("key", "qaclick123")  //set query parameter that will allow us to use this API
                .body(JSONPayloads.getDeletePlaceRequest(this.place_id))  //set body payload with updated address
                .when()                                     //when() marks sending of request
                .delete("maps/api/place/delete/json")    //run DELETE to a given endpoint
                .then()                                    //then() marks validation of response
                .assertThat()                              //assertThat() marks that we are going to use asserts
                .log().all()                               //log all
                .statusCode(200)                           //assert that status code is 200 (PUT was successful)
                .contentType("application/json")           //assert that content type is application/json
                .extract()                                 //extract is used when you want to get something from response
                .response().asString();                    //extract response as string, and save it to variable response (see fist line)

        System.out.println("JSON response of DELETE request is: " + response);
        //When we want to parse JSON response, we can use JsonPath
        //We will get name from GET request
        JsonPath js = new JsonPath(response);
        String status = js.getString("status");
        assertEquals("Status of DELETE operation is correct", "OK", status);

        //At this point, when we executed GET operation again, place is already deleted and we will end with error 404
        String response2 = given()                           //given() marks all input details
                .queryParam("key", "qaclick123")  //set query parameter that will allow us to use this API
                .queryParam("place_id", this.place_id)    //set query parameter that will specify which place we want to GET
                .when()                                     //when() marks sending of request
                .get("maps/api/place/get/json")          //run GET to a given endpoint
                .then()                                    //then() marks validation of response
                .assertThat()                              //assertThat() marks that we are going to use asserts
                .statusCode(404)                           //assert that status code is 404 (GET was unsuccessful - place is deleted)
                .extract()                                 //extract is used when you want to get something from response
                .response().asString();                    //extract response as string, and save it to variable response (see fist line)

        System.out.println("JSON response of failed GET request is: " + response2);

        //When we want to parse JSON response, we can use JsonPath
        //We will get name from GET request
        JsonPath js2 = new JsonPath(response2);
        String msg = js2.getString("msg");
        assertEquals("Status of failed GET operation is correct", "Get operation failed, looks like place_id  doesn't exists", msg);
    }

    /**
     * If you check our POST, PUT, GET, DELETE methods, you can see that we have some request parameters, that are identical in each test.
     * Also, we have some response validations, that are also identical in all tests.
     * In this test, we will setup RequestSpecBuilder and ResponseSpecBuilder objects, that can be used in all of our tests
     * in this class, and it will simplify our codebase when we are running multiple tests.
     * Note: in this example, we will run only one test just to demonstrate this concept.
     *       Imagine that you are running multiple tests, and you save pain of writing all requests and responses for each test.
     *
     * RequestSpecBuilder - utility that is used to construct a API request specifications.
     * ResponseSpecBuilder - utility that is used to verify an API responses.
     */
    @Test
    public void requestSpecBuilderExample() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        String response = given()
                .spec(requestSpecification)                 //for general request values, use our object
                .body(JSONPayloads.getAddPlaceRequest())    //for specific request values, use specific values
                .when()
                .post("maps/api/place/add/json")
                .then()
                .spec(responseSpecification)                //for general response asserts, use our object
                .body("scope", equalTo("APP"))    //for specific response assert, add specific assert
                .header("server", "Apache/2.4.18 (Ubuntu)")  //for specific response assert, just add this assert
                .extract()
                .response()
                .asString();

        System.out.println("Response of POST request: " + response);
        assertTrue("Status of POST request is correct", response.contains("\"status\":\"OK\""));
    }
}
