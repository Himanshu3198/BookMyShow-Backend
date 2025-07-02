package org.BookMyShow.Controller;

import jakarta.validation.Valid;
import org.BookMyShow.Dto.Request.BookingRequestDTO;
import org.BookMyShow.Dto.Response.BookingResponseDTO;
import org.BookMyShow.Entity.BookingHistory;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Mapper.BookingMapper;
import org.BookMyShow.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/place-booking")
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingHistory booking = bookingService.placeBooking(BookingMapper.toEntity(bookingRequestDTO));
        return ResponseEntity.status(201).body(BookingMapper.toDTO(booking));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable String bookingId) {
        BookingHistory cancelledBooking = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(cancelledBooking));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsForUser(@PathVariable Long userId) {
        List<BookingHistory> bookings = bookingService.getAllBookingsForUser(userId);
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException("No bookings found for user ID: " + userId);
        }
        return ResponseEntity.ok(bookings.stream().map(BookingMapper::toDTO).toList());
    }
}
