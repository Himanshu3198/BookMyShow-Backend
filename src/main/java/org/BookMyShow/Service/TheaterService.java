package org.BookMyShow.Service;

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

    public TheaterService(TheaterRepository theaterRepository,MovieService movieService){

        this.theaterRepository = theaterRepository;
        this.movieService = movieService;
    }

    public Theater addTheater(TheaterDTO theaterDTO){

        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.theaterName());
        theater.setLocation(theater.getLocation());

        for(var showDTO:theaterDTO.shows()){

            MovieShow movieShow = new MovieShow();
            movieShow.setShowTime(showDTO.showTime());
            Movie newMovie = movieService.getMovieById(showDTO.movieId());
            movieShow.setMovie(newMovie);


            for(var seatDTO:showDTO.seats()){
                Seat newSeat = new Seat();
                newSeat.setSeatNumber(seatDTO.seatNumber());
                newSeat.setStatus(seatDTO.status());
                movieShow.addSeat(newSeat);
            }
            theater.addShow(movieShow);
        }
      return theaterRepository.save(theater);

    }

    public Theater getTheaterById(Long id){
        return theaterRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Theater not found with id:"+id));
    }

    public List<Theater> getAllTheater(){
        return theaterRepository.findAll();
    }

    public void deleteTheaterById(Long id){
        if(!theaterRepository.existsById(id)){
            throw new ResourceNotFoundException("Theater not found with id:"+id);
        }
    }
}
