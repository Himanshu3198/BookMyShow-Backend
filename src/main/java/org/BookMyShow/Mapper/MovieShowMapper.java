package org.BookMyShow.Mapper;

import org.BookMyShow.Dto.Request.MovieShowDTO;
import org.BookMyShow.Dto.Response.MovieShowResponseDTO;
import org.BookMyShow.Entity.Movie;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.Seat;
public class MovieShowMapper {

    public static MovieShow toEntity(MovieShowDTO movieShowDTO, Movie movie){

        MovieShow movieShow = new MovieShow();
        movieShow.setMovie(movie);
        movieShow.setShowTime(movieShowDTO.showTime());

        for(var seatDTO:movieShowDTO.seats()){
            Seat seat = SeatMapper.toEntity(seatDTO);
            movieShow.addSeat(seat);
        }

        return movieShow;
    }

    public static MovieShowResponseDTO toDTO(MovieShow movieShow){
        return new MovieShowResponseDTO(
                movieShow.getId(),
                movieShow.getMovie().getTitle(),
                movieShow.getTheater().getTheaterName(),
                movieShow.getSeats(),
                movieShow.getStartTime(),
                movieShow.getEndTime()

        );
    }
}
