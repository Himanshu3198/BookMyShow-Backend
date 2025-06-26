package org.BookMyShow.Controller;


import org.BookMyShow.Dto.Request.BookingRequestDTO;
import org.BookMyShow.Dto.Response.BookingResponseDTO;
import org.BookMyShow.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking/")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookingResponseDTO>createBooking(BookingRequestDTO bookingRequestDTO){


    }
}
