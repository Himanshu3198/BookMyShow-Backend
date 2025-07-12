package bms.stepdefinitions;


import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.BookMyShow.Dto.Request.BookingRequestDTO;
import bms.util.TestDataLoader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingStepDefinitions {

    private Response response;
    private BookingRequestDTO bookingRequest;
    private final String baseUrl = "http://localhost:8080/api/bookings";

    @Given("a user, show and available seat exist")
    public void userShowSeatAvailable() {
        Long userId = TestDataLoader.getAsLong("booking.userId");
        Long showId = TestDataLoader.getAsLong("booking.showId");
        String seat = TestDataLoader.getAsString("booking.seat");
        Double amount = TestDataLoader.getAsDouble("booking.amount");

        bookingRequest = new BookingRequestDTO(seat, userId, showId, amount);
    }

    @Given("the seat is already booked for the configured show")
    public void seatAlreadyBooked() {
        String seat = TestDataLoader.getAsString("booking.alreadyBookedSeat");
        Long userId = TestDataLoader.getAsLong("booking.userId");
        Long showId = TestDataLoader.getAsLong("booking.showId");
        Double amount = TestDataLoader.getAsDouble("booking.amount");

        bookingRequest = new BookingRequestDTO(seat, userId, showId, amount);
        given().contentType(ContentType.JSON).body(bookingRequest).post(baseUrl);
    }

    @Given("a user and show are configured but seat is blank")
    public void userAndShowOnly() {
        Long userId = TestDataLoader.getAsLong("booking.userId");
        Long showId = TestDataLoader.getAsLong("booking.showId");

        bookingRequest = new BookingRequestDTO("", userId, showId, 300.0);
    }

    @Given("a valid booking exists")
    public void bookingWithIdExists() {
        // Assume a booking exists in DB. Or preload test data via @Before hook.
    }

    @When("the user places a booking")
    public void userBooksSeat() {
        response = given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .post(baseUrl);
    }

    @When("the user cancels a valid booking")
    public void cancelValidBooking() {
        String bookingId = TestDataLoader.getAsString("booking.validBookingId");
        response = given()
                .contentType(ContentType.JSON)
                .put(baseUrl + "/" + bookingId + "/cancel");
    }

    @When("the user cancels an invalid booking")
    public void cancelInvalidBooking() {
        String bookingId = TestDataLoader.getAsString("booking.invalidBookingId");
        response = given()
                .contentType(ContentType.JSON)
                .put(baseUrl + "/" + bookingId + "/cancel");
    }

    @Then("the response status should be {int}")
    public void validateStatus(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the booking status should be {string}")
    public void validateBookingStatus(String expectedStatus) {
        String actualStatus = response.jsonPath().getString("bookingStatus");
        assertThat(actualStatus, is(expectedStatus));
    }

    @Then("the error message should contain {string}")
    public void errorMessageShouldContain(String expectedMessage) {
        String error = response.getBody().asString();
        assertThat(error, containsString(expectedMessage));
    }
}
