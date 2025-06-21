package org.BookMyShow.Service;

import org.BookMyShow.Entity.Movie;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }

    public Movie getMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found with id: "+id));
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public void deleteMovieById(Long id){
        if(!movieRepository.existsById(id)){
            throw new ResourceNotFoundException("Movie not found with id:"+id);
        }
        movieRepository.deleteById(id);
    }
}
