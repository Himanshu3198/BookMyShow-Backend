package org.BookMyShow.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(SeatAlreadyBookedException.class)
  public ResponseEntity<String> handleSeatBooked(SeatAlreadyBookedException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }

  @ExceptionHandler(PaymentFailedException.class)
  public ResponseEntity<String> handlePaymentFailed(PaymentFailedException ex){
    return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(ex.getMessage());
  }

  @ExceptionHandler(BookingFailedException.class)
  public ResponseEntity<String> handleBooking(BookingFailedException ex){
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneric(Exception ex){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong"+ex.getMessage());
  }

}
