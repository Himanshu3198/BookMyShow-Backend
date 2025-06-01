package org.BookMyShow.Controller;


import org.BookMyShow.Dto.Request.MovieDTO;
import org.BookMyShow.Dto.Response.MovieResponseDTO;
import org.BookMyShow.Entity.Movie;
import org.BookMyShow.Mapper.MovieMapper;
import org.BookMyShow.Service.MovieService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("/create")
    public ResponseEntity<MovieResponseDTO> createMovie(MovieDTO movieDTO){

        Movie movie = MovieMapper.toEntity(movieDTO);
        movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toDTO(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id){
        Movie movie =movieService.getMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).body(MovieMapper.toDTO(movie));
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<MovieResponseDTO>> getAllMovies(){
//        List<MovieResponseDTO>  = movieService.getAllMovies();
//    }
}
