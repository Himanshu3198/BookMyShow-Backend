package org.BookMyShow.Controller;


import jakarta.validation.Valid;
import org.BookMyShow.Dto.Request.BookingRequestDTO;
import org.BookMyShow.Dto.Response.BookingResponseDTO;
import org.BookMyShow.Entity.BookingHistory;
import org.BookMyShow.Mapper.BookingMapper;
import org.BookMyShow.Service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking/")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/place-booking")
    public ResponseEntity<BookingResponseDTO>createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO){

          BookingHistory makeBooking = bookingService.placeBooking(BookingMapper.toEntity(bookingRequestDTO));
          return ResponseEntity.status(HttpStatus.CREATED).body(BookingMapper.toDTO(makeBooking));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> cancelBookings(@PathVariable String bookingId){
        BookingHistory   bookingRecord = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(BookingMapper.toDTO(bookingRecord));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingForUser(@PathVariable Long id){
        List<BookingHistory> bookingRecord = bookingService.getAllBookingsForUser(id);
        return  ResponseEntity.status(HttpStatus.FOUND).body(bookingRecord.stream().map(BookingMapper::toDTO).toList());
    }
}
