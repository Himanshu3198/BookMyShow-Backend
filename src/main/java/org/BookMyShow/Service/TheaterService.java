package org.BookMyShow.Service;

import jakarta.transaction.Transactional;
import org.BookMyShow.Dto.Request.MovieShowDTO;
import org.BookMyShow.Dto.Request.SeatDTO;
import org.BookMyShow.Dto.Request.TheaterDTO;
import org.BookMyShow.Entity.Movie;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.Seat;
import org.BookMyShow.Entity.Theater;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final MovieService movieService;

    public TheaterService(TheaterRepository theaterRepository, MovieService movieService) {
        this.theaterRepository = theaterRepository;
        this.movieService = movieService;
    }

    @Transactional
    public Theater addTheater(TheaterDTO theaterDTO) {
        if (theaterDTO.shows() == null || theaterDTO.shows().isEmpty()) {
            throw new IllegalArgumentException("At least one show is required.");
        }

        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.theaterName());
        theater.setLocation(theaterDTO.location());

        for (MovieShowDTO showDTO : theaterDTO.shows()) {
            theater.addShow(buildMovieShow(showDTO));
        }

        return theaterRepository.save(theater);
    }

    private MovieShow buildMovieShow(MovieShowDTO showDTO) {
        MovieShow movieShow = new MovieShow();
        movieShow.setShowTime(showDTO.showTime());
        movieShow.setStartTime(showDTO.startTime());
        movieShow.setEndTime(showDTO.endTime());

        Movie movie = movieService.getMovieById(showDTO.movieId());
        movieShow.setMovie(movie);

        if (showDTO.seats() == null || showDTO.seats().isEmpty()) {
            throw new IllegalArgumentException("Each show must contain at least one seat.");
        }

        for (SeatDTO seatDTO : showDTO.seats()) {
            if (seatDTO.status() == null || seatDTO.seatNumber() == null || seatDTO.seatNumber().isBlank()) {
                throw new IllegalArgumentException("Each seat must have a valid number and status.");
            }

            Seat seat = new Seat();
            seat.setSeatNumber(seatDTO.seatNumber());
            seat.setStatus(seatDTO.status());
            movieShow.addSeat(seat);
        }

        return movieShow;
    }

    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found with id: " + id));
    }

    public List<Theater> getAllTheater() {
        return theaterRepository.findAll();
    }

    public void deleteTheaterById(Long id) {
        Theater theater = getTheaterById(id); // Reuse existing method
        theaterRepository.delete(theater);
    }
}