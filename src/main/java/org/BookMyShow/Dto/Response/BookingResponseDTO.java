package org.BookMyShow.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Enum.BookingStatus;
import org.BookMyShow.Enum.PaymentType;

import java.time.LocalDateTime;

public record BookingResponseDTO (

        @JsonProperty(value = "bookingId")
        String bookingId,
        @JsonProperty(value = "userId")
        Long userId,
        @JsonProperty(value = "movieShow")
        MovieShow movieShow,
        @JsonProperty(value = "amount")
        Double amount,
        @JsonProperty(value = "bookingStatus")
        BookingStatus bookingStatus,
        @JsonProperty(value = "paymentType")
        PaymentType paymentType,
        @JsonProperty(value = "bookingTime")
        LocalDateTime bookingTime

) {}
