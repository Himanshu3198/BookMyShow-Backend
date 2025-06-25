package org.BookMyShow.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.BookMyShow.Enum.Status;

public record SeatDTO(
        @NotBlank(message = "Seat number cannot be blank")
        String seatNumber,

        @NotNull(message = "Seat status cannot be null")
        Status status
) {}

