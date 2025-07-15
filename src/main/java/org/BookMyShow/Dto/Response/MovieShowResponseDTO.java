package org.BookMyShow.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record MovieShowResponseDTO(
        @JsonProperty("showId") Long showId,
        @JsonProperty("movieName") String movieName,
        @JsonProperty("theaterName") String theaterName,
        @JsonProperty("seatNumbers") List<org.BookMyShow.Entity.Seat> seatNumbers,
        @JsonProperty("startTime") LocalDateTime startTime,
        @JsonProperty("endTime") LocalDateTime endTime
) {}
