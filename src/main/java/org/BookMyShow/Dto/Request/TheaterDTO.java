package org.BookMyShow.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TheaterDTO (

        @NotBlank(message = "theater name cannot be blank")
        @JsonProperty(value = "theaterName")
        String theaterName,
        @NotBlank(message = "location cannot be blank")
        @JsonProperty(value = "location")
        String location,
        List<MovieShowDTO> shows
){}
