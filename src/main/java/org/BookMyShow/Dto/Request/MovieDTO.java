package org.BookMyShow.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDTO (

    @NotBlank(message ="movie title cannot be blank")
    @JsonProperty(value = "title")
    @NotNull(message = "Movie cannot be null")
    String title,
    @NotNull(message = "Genre cannot be null")
    String genre,
    @NotNull(message = "Cast cannot be null")
    String cast

){}
