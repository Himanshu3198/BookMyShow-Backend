package org.BookMyShow.Mapper;

import org.BookMyShow.Dto.Request.MovieShowDTO;
import org.BookMyShow.Dto.Response.MovieShowResponseDTO;
import org.BookMyShow.Dto.Response.SeatResponseDTO;
import org.BookMyShow.Entity.Movie;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class MovieShowMapper {

    /**
     * Converts a MovieShowDTO to a MovieShow entity with linked Movie and Seats.
     */
    public static MovieShow toEntity(MovieShowDTO movieShowDTO, Movie movie) {
        MovieShow movieShow = new MovieShow();
        movieShow.setMovie(movie);
        movieShow.setShowTime(movieShowDTO.showTime());

        movieShowDTO.seats().forEach(seatDTO -> {
            Seat seat = SeatMapper.toEntity(seatDTO);
            movieShow.addSeat(seat);  // Ensures bidirectional linking if implemented
        });

        return movieShow;
    }

    /**
     * Converts a MovieShow entity to a MovieShowResponseDTO, mapping nested seats safely.
     */
    public static MovieShowResponseDTO toDTO(MovieShow movieShow) {
        List<SeatResponseDTO> seatDTOs = movieShow.getSeats().stream()
                .map(SeatMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new MovieShowResponseDTO(
                movieShow.getId(),
                movieShow.getMovie().getTitle(),
                movieShow.getTheater().getTheaterName(),
                seatDTOs,
                movieShow.getStartTime(),
                movieShow.getEndTime()
        );
    }
}
