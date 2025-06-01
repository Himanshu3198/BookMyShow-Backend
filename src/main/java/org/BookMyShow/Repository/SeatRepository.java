package org.BookMyShow.Repository;

import org.BookMyShow.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
