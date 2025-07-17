package org.BookMyShow.Util.Worker;

import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.Seat;
import org.BookMyShow.Enum.SeatStatus;
import org.BookMyShow.Repository.SeatRepository;
import org.BookMyShow.Service.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SeatAvailabilityScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeatAvailabilityScheduler.class);

    private final ShowService showService;
    private final SeatRepository seatRepository;

    public SeatAvailabilityScheduler(ShowService showService, SeatRepository seatRepository) {
        this.showService = showService;
        this.seatRepository = seatRepository;
    }

    @Scheduled(fixedRate = 600000) // every 10 minutes
    public void updateSeatStatus() {
        try {
            List<MovieShow> movieShows = showService.getAllShow();

            if (movieShows.isEmpty()) {
                LOGGER.info("No shows found to process for seat status update.");
                return;
            }

            movieShows.stream()
                    .filter(show -> LocalDateTime.now().isAfter(show.getEndTime()))
                    .forEach(show -> {
                        try {
                            List<Seat> seats = show.getSeats();
                            seats.forEach(seat -> seat.setStatus(SeatStatus.AVAILABLE));
                            seatRepository.saveAll(seats);
                            LOGGER.info("Marked all seats as AVAILABLE for show ID: {}", show.getId());
                        } catch (Exception e) {
                            LOGGER.error("Error while updating seat status for show ID: {}", show.getId(), e);
                        }
                    });

        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred in scheduled seat status updater", e);
        }
    }
}
