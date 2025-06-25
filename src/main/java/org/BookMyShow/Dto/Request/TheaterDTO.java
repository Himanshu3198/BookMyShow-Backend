package org.BookMyShow.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TheaterDTO(

        @NotBlank(message = "Theater name cannot be blank")
        @JsonProperty("theaterName")
        String theaterName,

        @NotBlank(message = "Location cannot be blank")
        @JsonProperty("location")
        String location,

        @NotNull(message = "Shows list cannot be null")
        List<@NotNull MovieShowDTO> shows

) {}
