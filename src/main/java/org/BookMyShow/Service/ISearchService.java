package org.BookMyShow.Service;

import org.BookMyShow.Entity.MovieShow;

import java.util.List;

public interface ISearchService {

    List<MovieShow> filterShowByMovie(String movieName);
    List<MovieShow> filterShowByTheater(String theaterName);
}
