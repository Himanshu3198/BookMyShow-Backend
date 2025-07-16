package org.BookMyShow.Service;


import jakarta.transaction.Transactional;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Exception.ShowNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements  ISearchService{

    private final ShowService showService;

    public SearchServiceImpl(ShowService showService){
        this.showService = showService;
    }
    @Override
    @Transactional
    public List<MovieShow> filterShowByMovie(String movieName) {
        try {
            List<MovieShow> movieShows = showService.getAllShow();
            if(movieShows == null || movieShows.isEmpty()){
                throw new ShowNotFoundException("Shows not found!");
            }
            return movieShows.stream().filter((MovieShow show)->{
                return show.getMovie().equals(movieName);
            }).collect(Collectors.toList());
        }catch (ShowNotFoundException ex){
            throw new ShowNotFoundException("Shows not found for this Movie");
        }

    }

    @Override
    public List<MovieShow> filterShowByTheater(String theaterName) {
        return List.of();
    }
}
