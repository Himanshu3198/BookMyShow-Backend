package org.BookMyShow.Repository;

import org.BookMyShow.Entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Long> {
}
