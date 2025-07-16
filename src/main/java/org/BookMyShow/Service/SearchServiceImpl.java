package org.BookMyShow.Service;


import jakarta.transaction.Transactional;
import org.BookMyShow.Entity.Movie;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Exception.ShowNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements ISearchService {

    private final ShowService showService;

    public SearchServiceImpl(ShowService showService) {
        this.showService = showService;
    }

    @Override
    @Transactional
    public List<MovieShow> filterShowByMovie(String movieName) {

        List<MovieShow> movieShows = showService.getAllShow();
        if (movieShows == null || movieShows.isEmpty()) {
            throw new ShowNotFoundException("Shows not found!");
        }
        Predicate<MovieShow> moviePredicate = show ->
                movieName.equalsIgnoreCase(show.getMovie().getTitle());
        List<MovieShow> filteredShows = movieShows.stream().filter(moviePredicate).toList();
        if (filteredShows.isEmpty()) {
            throw new ShowNotFoundException("Shows not found for this Movie"+movieName);
        }
        return filteredShows;
    }

    @Override
    public List<MovieShow> filterShowByTheater(String theaterName) {
        List<MovieShow> movieShows = showService.getAllShow();
        if (movieShows == null || movieShows.isEmpty()) {
            throw new ShowNotFoundException("Shows not found!");
        }
        Predicate<MovieShow> moviePredicate = show -> theaterName.equalsIgnoreCase(show.getTheater().getTheaterName());
        List<MovieShow> filteredShows = movieShows.stream().filter(moviePredicate).toList();

        if(filteredShows.isEmpty()){
            throw new ShowNotFoundException("Shows not found for theater:"+theaterName);
        }
        return filteredShows;
    }
}
