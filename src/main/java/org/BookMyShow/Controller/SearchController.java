package org.BookMyShow.Controller;

import org.BookMyShow.Dto.Response.MovieShowResponseDTO;
import org.BookMyShow.Mapper.MovieShowMapper;
import org.BookMyShow.Service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @GetMapping("/byMovie")
    public ResponseEntity<List<MovieShowResponseDTO>> getShowByMovie(@RequestParam String movieName) {
        List<MovieShowResponseDTO> movieShows = searchService
                .filterShowByMovie(movieName)
                .stream()
                .map(MovieShowMapper::toDTO)
                .toList();

        return ResponseEntity.ok(movieShows);
    }

    @GetMapping("/byTheater")
    public ResponseEntity<List<MovieShowResponseDTO>> getShowByTheater(@RequestParam String theaterName){
        List<MovieShowResponseDTO> movieShows = searchService
                .filterShowByTheater(theaterName)
                .stream()
                .map(MovieShowMapper::toDTO)
                .toList();

        return ResponseEntity.ok(movieShows);
    }

}
