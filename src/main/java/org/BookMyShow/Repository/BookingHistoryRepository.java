package org.BookMyShow.Repository;

import org.BookMyShow.Entity.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistory,Long> {
    List<BookingHistory> findByUserId(Long userId);
    Optional<BookingHistory> findByBookingId(String bookingId);
}
