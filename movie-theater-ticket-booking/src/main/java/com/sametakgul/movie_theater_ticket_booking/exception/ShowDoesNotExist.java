package com.sametakgul.movie_theater_ticket_booking.exception;

public class ShowDoesNotExist extends RuntimeException{

    private static final long serialVersionUID = -4436119261176031165L;

    public ShowDoesNotExist() {
        super("Show Does Not Exist");
    }
}
