package org.BookMyShow.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record MovieShowDTO (
        @JsonProperty(value = "showTime")
        @NotNull(message = "Showtime cannot be null")
        String showTime,
        @JsonProperty(value = "movieId")
        @NotNull(message = "MovieId cannot be null")
        Long movieId,
        @NotNull(message = "Show start time cannot be null")
        LocalDateTime startTime,
        @NotNull(message = "Show end time cannot be null")
        LocalDateTime endTime,
        @NotNull(message = "Seats cannot be null")
        List<@Valid SeatDTO> seats
){}
