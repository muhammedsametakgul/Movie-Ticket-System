package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.response.TicketResponse;

public class TicketMapper {

    public static TicketResponse returnTicket(Ticket ticket) {
        TicketResponse ticketResponseDto = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(ticket.getShow().getTheater().getAddress())
                .theaterName(ticket.getShow().getTheater().getName())
                .movieName(ticket.getShow().getMovie().getMovieName())
                .date(ticket.getShow().getDate())
                .time(ticket.getShow().getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();

        return ticketResponseDto;
    }
}
