package com.sametakgul.movie_theater_ticket_booking.exception;

public class TheaterAlreadyExists extends RuntimeException{

    private static final long serialVersionUID = 6386810783666583528L;

    public TheaterAlreadyExists() {
        super("There is already a theater");
    }
}
