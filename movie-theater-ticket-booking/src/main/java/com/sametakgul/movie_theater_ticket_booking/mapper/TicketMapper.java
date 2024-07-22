package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.Ticket;
import com.sametakgul.movie_theater_ticket_booking.response.TicketResponse;

public class TicketMapper {

    public static TicketResponse returnTicket(Show show, Ticket ticket) {
        TicketResponse ticketResponseDto = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(show.getTheater().getAddress())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .date(show.getDate())
                .time(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();

        return ticketResponseDto;
    }
}
