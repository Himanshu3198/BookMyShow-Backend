package org.BookMyShow.Model;

public record BookingModel (
        String seatNumber,
        Long userId,
        Long showId,
        Double amount
)
{}
