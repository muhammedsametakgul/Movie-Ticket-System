package com.sametakgul.movie_theater_ticket_booking.exception;

public class TicketBookingFailedException extends RuntimeException {
    private static final long serialVersionUID = 1497113945165128412L;

    public TicketBookingFailedException() {
        super("Ticket Booking Failed");
    }
}
