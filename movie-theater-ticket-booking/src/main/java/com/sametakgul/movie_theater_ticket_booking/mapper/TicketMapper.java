package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.response.TicketResponse;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<TicketResponse> returnTicket(List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketMapper::returnTicket)
                .collect(Collectors.toList());
    }
}
