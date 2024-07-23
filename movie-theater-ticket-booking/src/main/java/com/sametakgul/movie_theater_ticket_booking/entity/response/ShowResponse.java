package com.sametakgul.movie_theater_ticket_booking.entity.response;

import com.sametakgul.movie_theater_ticket_booking.entity.model.Movie;
import com.sametakgul.movie_theater_ticket_booking.entity.model.ShowSeat;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Theater;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponse {

    private Integer showId;
    private Time time;
    private Date date;
    private String movieName;
    private String theaterName;
    private String theaterAddress;
    private List<ShowSeatResponse> showSeatList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();
}
