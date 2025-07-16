package org.BookMyShow.Mapper;

import org.BookMyShow.Dto.Request.BookingRequestDTO;
import org.BookMyShow.Dto.Response.BookingResponseDTO;
import org.BookMyShow.Entity.BookingHistory;
import org.BookMyShow.Model.BookingModel;

public class BookingMapper {

    public static BookingModel toEntity(BookingRequestDTO bookingRequestDTO){
        return new BookingModel(bookingRequestDTO.seatNumber(),
                bookingRequestDTO.userId(),
                bookingRequestDTO.showId(),
                bookingRequestDTO.amount());
    }

    public static BookingResponseDTO toDTO(BookingHistory booking){
        return new BookingResponseDTO(
                booking.getBookingId(),
                booking.getUser().getId(),
                MovieShowMapper.toDTO(booking.getShow()),
                booking.getAmount(),
                booking.getSeatNumber(),
                booking.getBookingStatus(),
                booking.getpaymentType(),
                booking.getBookingTime()
        );
    }
}

