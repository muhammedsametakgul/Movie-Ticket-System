package com.sametakgul.movie_theater_ticket_booking.exception;

public class TheaterDoesNotExist extends RuntimeException{
    private static final long serialVersionUID = 2885350098352987873L;

    public TheaterDoesNotExist( ) {
        super("Theater does not Exists");
    }
}
