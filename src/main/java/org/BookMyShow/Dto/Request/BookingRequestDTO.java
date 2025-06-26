package org.BookMyShow.Dto.Request;

import jakarta.validation.constraints.NotNull;

public record BookingRequestDTO(
        @NotNull(message ="Seat number cannot be null")
        String seatNumber,
        @NotNull(message = "UserId cannot be null")
        Long userId,
        @NotNull(message = "ShowId cannot be null")
        Long showId,
        @NotNull(message = "Amount cannot be null")
        Double amount

) {
}
