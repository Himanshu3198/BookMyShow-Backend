package org.BookMyShow.Repository;

import org.BookMyShow.Entity.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<MovieShow,Long> {
}
