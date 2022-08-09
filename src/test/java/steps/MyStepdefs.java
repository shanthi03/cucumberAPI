package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;


import static io.restassured.RestAssured.given;


public class MyStepdefs {
    int userId;
    JsonPath jsonPath;
    Response putResponse;

    @Given("a user")
    public void aUser() {
        // create request
        String email = "\"testuser" + System.currentTimeMillis() + "@15ce.com\"";
        String requestBody = "{\"name\":\"Tenali Ramakrishna\",\"gender\":\"male\",\"email\":" + email + "," +
                "\"status\":\"active\"}";


        Response response = given()
                .body(requestBody)
                .when().post("users")
                .then().statusCode(201).extract().response();

        jsonPath = new JsonPath(response.asString());
        userId = jsonPath.getInt("id");


    }

    @When("updating the user")
    public void updatingTheUser() {
        //put request
        String email = "\"testuser" + System.currentTimeMillis() + "@15ce.com\"";
        String putRequestBody = "{\"name\":\"Tenali Ramakrishna\",\"gender\":\"male\",\"email\":" + email + "," +
                "\"status\":\"active\"}";

        Response putResponse = given()
                .body(putRequestBody)
                .when().put("users/" + userId)
                .then().statusCode(200).extract().response();

        jsonPath = new JsonPath(putResponse.asString());

    }

    @Then("the user is updated")
    public void theUserIsUpdated() {
        // assertion
        jsonPath = new JsonPath(putResponse.asString());
        Assert.assertEquals(jsonPath.getString("gender"), "female");
    }


    @When("updating the user with invalid input")
    public void updatingTheUserWithInValidInput() {
    }

    @Then("the user not to be  updated")
    public void theUserNotToBeUpdated() {
    }


    @When("^updating the user with invalid input \"(.*)\" and \"(.*)\"$")
    public void updatingTheUserWithInvalidInput(String arg0, String arg1) {
        System.out.println(arg0);
        System.out.println(arg1);

    }
}