package bms.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.RestAssured;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class PlaceBookingStepDefs {

    private Response response;

    @Given("the booking API is available")
    public void the_booking_api_is_available() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @When("I place a booking with seatNumber {string}, userId {int}, showId {int}, and amount {double}")
    public void i_place_a_booking(String seatNumber, int userId, int showId, double amount) {
        Map<String, Object> request = new HashMap<>();
        request.put("seatNumber", seatNumber);
        request.put("userId", userId);
        request.put("showId", showId);
        request.put("amount", amount);

        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post("/api/booking/place-booking");
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should contain bookingId and bookingStatus {string}")
    public void the_response_should_contain_bookingId_and_bookingStatus(String expectedStatus) {
        String bookingId = response.jsonPath().getString("bookingId");
        String bookingStatus = response.jsonPath().getString("bookingStatus");

        assertThat(bookingId, notNullValue());
        assertThat(bookingStatus, equalTo(expectedStatus));
    }
}
