package org.BookMyShow.Mapper;

import org.BookMyShow.Dto.Request.SeatDTO;
import org.BookMyShow.Entity.Seat;

public class SeatMapper {

    public static Seat toEntity(SeatDTO dto){

        Seat seat = new Seat();
        seat.setSeatNumber(dto.seatNumber());
        seat.setStatus(dto.status());
        return seat;
    }
}
