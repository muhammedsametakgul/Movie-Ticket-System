package com.sametakgul.movie_theater_ticket_booking.exception;

public class MovieDoesNotExist extends RuntimeException {
    private static final long serialVersionUID = -5385129013790060351L;

    public MovieDoesNotExist() {
        super("Movie dose not Exists");
    }
}