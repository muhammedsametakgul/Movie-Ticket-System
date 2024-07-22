package com.sametakgul.movie_theater_ticket_booking.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private Time time;
    private Date date;
    private String movieName;
    private String theaterName;
    private String address;
    private String bookedSeats;
    private Integer totalPrice;
}