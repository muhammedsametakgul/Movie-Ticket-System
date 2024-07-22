package com.sametakgul.movie_theater_ticket_booking.exception;


public class SeatsNotAvailable extends RuntimeException {
    private static final long serialVersionUID = 1497113945165128412L;

    public SeatsNotAvailable() {
        super("Requested Seats Are Not Available");
    }
}