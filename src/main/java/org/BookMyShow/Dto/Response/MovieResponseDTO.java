package org.BookMyShow.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.BookMyShow.Entity.Movie;

public record MovieResponseDTO(

      @JsonProperty(value = "movieId")
      Long movieId,
      @JsonProperty(value = "title")
      String title,
      @JsonProperty(value = "cast")
      String cast,
      @JsonProperty(value = "genre")
      String genre


){}
