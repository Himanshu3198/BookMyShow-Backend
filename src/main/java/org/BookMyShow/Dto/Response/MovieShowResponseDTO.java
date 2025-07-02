package org.BookMyShow.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record MovieShowResponseDTO(
        @JsonProperty("showId") Long showId,
        @JsonProperty("movieName") String movieName,
        @JsonProperty("theaterName") String theaterName
) {}
