Feature: Place Booking API Regression

  Scenario: Successfully place a booking
    Given the booking API is available
    When I place a booking with seatNumber "D2", userId 2, showId 11, and amount 650.0
    Then the response status code should be 201
    And the response should contain bookingId and bookingStatus "CONFIRMED"
