package org.BookMyShow.Exception;

public class BookingFailedException extends RuntimeException {
    public BookingFailedException(String message) {
        super(message);
    }
}
