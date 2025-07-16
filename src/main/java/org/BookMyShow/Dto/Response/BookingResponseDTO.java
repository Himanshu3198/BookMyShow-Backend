package org.BookMyShow.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Enum.BookingStatus;
import org.BookMyShow.Enum.PaymentType;

import java.time.LocalDateTime;

public record BookingResponseDTO (

        @JsonProperty("bookingId") String bookingId,
        @JsonProperty("userId") Long userId,
        @JsonProperty("movieShow") MovieShowResponseDTO movieShow,
        @JsonProperty("amount") Double amount,
        @JsonProperty("seatNumber") String seatNumber,
        @JsonProperty("bookingStatus") BookingStatus bookingStatus,
        @JsonProperty("paymentType") PaymentType paymentType,
        @JsonProperty("bookingTime") LocalDateTime bookingTime

) {}
