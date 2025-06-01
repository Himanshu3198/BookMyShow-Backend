package org.BookMyShow.Mapper;

import org.BookMyShow.Dto.Request.MovieDTO;
import org.BookMyShow.Dto.Response.MovieResponseDTO;
import org.BookMyShow.Entity.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieDTO movieDTO){
        return new Movie(movieDTO.title(),movieDTO.cast(),movieDTO.genre());
    }

    public static MovieResponseDTO toDTO(Movie movie){
        return new MovieResponseDTO(movie.getId(), movie.getTitle(), movie.getGenre(), movie.getGenre());
    }
}
