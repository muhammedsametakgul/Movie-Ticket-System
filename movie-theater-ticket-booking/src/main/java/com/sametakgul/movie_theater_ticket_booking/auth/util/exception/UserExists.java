package com.sametakgul.movie_theater_ticket_booking.auth.util.exception;

public class UserExists extends RuntimeException {
    private static final long serialVersionUID = -4666349320340656440L;

    public UserExists() {
        super("User Already Exists with this EmailId");
    }
}