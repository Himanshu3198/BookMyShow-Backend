Feature: Booking API with Dynamic Parameters

  Scenario Outline: Place a successful booking
    Given a user with id <userId> and a show with id <showId> and seat "<seat>" is available
    When the user books seat "<seat>" with amount <amount>
    Then the response status should be <status>
    And the booking status should be "<bookingStatus>"

    Examples:
      | userId | showId | seat | amount | status | bookingStatus |
      | 1      | 1      | A1   | 300.0  | 201    | CONFIRMED     |

  Scenario Outline: Fail booking when seat is already booked
    Given the seat "<seat>" is already booked for show id <showId>
    When the user books seat "<seat>" with amount <amount>
    Then the response status should be <status>
    And the error message should contain "<errorMsg>"

    Examples:
      | showId | seat | amount | status | errorMsg              |
      | 1      | A1   | 300.0  | 400    | Seat is not available |

  Scenario Outline: Cancel a booking with valid/invalid bookingId
    When the user cancels the booking with id "<bookingId>"
    Then the response status should be <status>
    And the booking status should be "<expectedStatus>"

    Examples:
      | bookingId   | status | expectedStatus |
      | abcd1234    | 200    | CANCELLED      |
      | invalid123  | 404    | NOT_FOUND      |