package org.BookMyShow.Repository;

import org.BookMyShow.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    Optional<Seat> findBySeatNumber(String seatNumber, Long showId);
}
