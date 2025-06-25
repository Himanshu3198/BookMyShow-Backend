package org.BookMyShow.Service;

import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.Seat;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Repository.SeatRepository;
import org.BookMyShow.Repository.ShowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowService.class);

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    /**
     * Get show by ID with proper exception handling.
     */
    public MovieShow getShowById(Long id) {
        try {
            return getShowOrThrow(id);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch show with id: {}", id, e);
            throw e;
        }
    }

    /**
     * Get seat by seat number and show ID
     */
    public Seat getSeatByNumber(String seatNumber, Long showId) {
        try {
            MovieShow movieShow = getShowOrThrow(showId);
            Seat seat = movieShow.getSeatByNumber(seatNumber);
            if (seat == null) {
                throw new ResourceNotFoundException("Seat number " + seatNumber + " not found in show " + showId);
            }
            return seat;
        } catch (Exception e) {
            LOGGER.error("Failed to fetch seat {} for show {}", seatNumber, showId, e);
            throw e;
        }
    }

    /**
     * Get all seats for a given show ID
     */
    public List<Seat> getAllSeats(Long showId) {
        try {
            MovieShow movieShow = getShowOrThrow(showId);
            return movieShow.getSeats();
        } catch (Exception e) {
            LOGGER.error("Failed to fetch seats for show {}", showId, e);
            throw e;
        }
    }

    /**
     * Private helper to fetch MovieShow or throw consistent error
     */
    private MovieShow getShowOrThrow(Long showId) {
        return showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found for ID: " + showId));
    }
}
