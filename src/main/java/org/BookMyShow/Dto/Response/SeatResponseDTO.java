package org.BookMyShow.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SeatResponseDTO(
        @JsonProperty("seatId") Long seatId,
        @JsonProperty("seatNumber") String seatNumber,
        @JsonProperty("status") String status
) {}