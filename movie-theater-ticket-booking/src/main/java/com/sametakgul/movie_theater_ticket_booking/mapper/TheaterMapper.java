package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.Theater;
import com.sametakgul.movie_theater_ticket_booking.request.TheaterRequest;

public class TheaterMapper {

    public static Theater theaterDtoToTheater(TheaterRequest theaterRequest) {
        Theater theater = Theater.builder()
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();
        return theater;
    }
}
