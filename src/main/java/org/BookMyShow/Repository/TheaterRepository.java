package org.BookMyShow.Repository;

import org.BookMyShow.Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
}
