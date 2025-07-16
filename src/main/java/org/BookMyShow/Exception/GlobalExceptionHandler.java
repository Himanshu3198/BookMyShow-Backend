package org.BookMyShow.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){
    ErrorResponse response = new ErrorResponse("RESOURCE_NOT_FOUND","CANNOT FOUND RESOURCE",ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(SeatAlreadyBookedException.class)
  public ResponseEntity<ErrorResponse> handleSeatBooked(SeatAlreadyBookedException ex) {
    ErrorResponse response = new ErrorResponse("SEAT_NOT_AVAILABLE","THIS SEAT IS NOT AVAILABLE",ex.getMessage());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(PaymentFailedException.class)
  public ResponseEntity<ErrorResponse> handlePaymentFailed(PaymentFailedException ex){
    ErrorResponse response = new ErrorResponse("PAYMENT_FAILED","PAYMENT COUNT NOT PROCESSED",ex.getMessage());
    return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
  }

  @ExceptionHandler(BookingFailedException.class)
  public ResponseEntity<ErrorResponse> handleBooking(BookingFailedException ex){
    ErrorResponse response = new ErrorResponse("BOOKING_FAILED","BOOKING FAILED FOR THIS SEAT",ex.getMessage());

    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneric(Exception ex){
    ErrorResponse response = new ErrorResponse("SERVER ERROR","SOMETHING WHET WRONG",ex.getMessage());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(ShowNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleShowNotFound(ShowNotFoundException ex){
    ErrorResponse response = new ErrorResponse("SHOW_NOT_FOUND","Show is not available ", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

}
