package org.BookMyShow.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDTO (

    @NotBlank(message ="movie title cannot be blank")
    @JsonProperty(value = "title")
    String title,
    String genre,
    @NotNull(message = "cast cannot be null")
    String cast

){}
