package com.sametakgul.movie_theater_ticket_booking.auth.util.exception;

public class UserDoesNotExist extends RuntimeException{

    private static final long serialVersionUID = 264309547420961862L;

    public UserDoesNotExist() {
        super("User Does Not Exist");
    }
}
