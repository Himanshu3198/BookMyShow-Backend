package org.BookMyShow.Mapper;

import org.BookMyShow.Dto.Request.TheaterDTO;
import org.BookMyShow.Dto.Response.TheaterResponseDTO;
import org.BookMyShow.Entity.Movie;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.Theater;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Service.MovieService;

public class TheaterMapper {

    public static Theater toEntity(TheaterDTO theaterDTO, MovieService movieService) throws ResourceNotFoundException{
        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.theaterName());
        theater.setLocation(theaterDTO.location());

        for(var showDTO:theaterDTO.shows()){
            Movie movie = movieService.getMovieById(showDTO.movieId());
            MovieShow show = MovieShowMapper.toEntity(showDTO,movie);
            theater.addShow(show);
        }

        return theater;
    }

    public static TheaterResponseDTO toDTO(Theater theater){
        return new TheaterResponseDTO(theater.getId(),theater.getTheaterName(), theater.getLocation());
    }
}
