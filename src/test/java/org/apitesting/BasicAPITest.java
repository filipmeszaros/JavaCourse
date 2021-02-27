package org.apitesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apitesting.payloads.Payload;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAPITest {
    public static void main(String[] args) {
        //given - all input details
        //when  - submit API
        //then  - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.getAddPlaceRequest())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); //for parsing Json
        String placeId = js.getString("place_id");

        System.out.println("Place id from response is: " + placeId);

        //Update Place
        String newAddress = "New Address 123, Africa";

        given().log().all().queryParam("key", "qaclick123").
                header("Content-Type", "application/json")
                .body("{\r\n" +
                        "\"place_id\":\"" + placeId + "\",\r\n" +
                        "\"address\":\"" + newAddress + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                when().put("maps/api/place/update/json")
                .then().assertThat().log().all()
                .statusCode(200).
                body("msg", equalTo("Address successfully updated"));

        //Get Place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all()
                .statusCode(200)
                .extract().response().asString();
        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println("Current address: " + actualAddress);
        Assert.assertEquals(actualAddress, "Pacific ocean");
    }
}
