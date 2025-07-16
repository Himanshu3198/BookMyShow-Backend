package org.BookMyShow.Mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.BookMyShow.Dto.Request.SeatDTO;
import org.BookMyShow.Dto.Response.SeatResponseDTO;
import org.BookMyShow.Entity.Seat;

public class SeatMapper {

    public static Seat toEntity(SeatDTO dto){

        Seat seat = new Seat();
        seat.setSeatNumber(dto.seatNumber());
        seat.setStatus(dto.status());
        return seat;
    }
    public static SeatResponseDTO toResponseDTO(Seat seat) {
        return new SeatResponseDTO(
                seat.getId(),
                seat.getSeatNumber(),
                seat.getStatus().name()
        );
    }
}
