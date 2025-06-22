package org.BookMyShow.Dto.Request;

import java.util.List;

public record MovieShowDTO (
        String showTime,
        Long movieId,
        List<SeatDTO> seats
){}
