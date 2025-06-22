package org.BookMyShow.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.BookMyShow.Enum.Status;

public record SeatDTO (

        @NotBlank(message = "Seat number cannot be blank")
        @JsonProperty(value = "seatNumber")
        String seatNumber,

        @JsonProperty(value = "seatStatus")
        Status status
)
{}
