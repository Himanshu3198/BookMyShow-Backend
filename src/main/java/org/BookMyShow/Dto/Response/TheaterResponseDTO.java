package org.BookMyShow.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TheaterResponseDTO(

        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "theaterName")
        String theaterName,
        @JsonProperty(value = "location")
        String location
) {
}
