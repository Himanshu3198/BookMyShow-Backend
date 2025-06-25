package org.BookMyShow.Repository;

import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow,Long> {
}
