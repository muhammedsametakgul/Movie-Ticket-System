package com.sametakgul.movie_theater_ticket_booking.entity.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class EmailSendRequest {
    private String email;
    private String message;
}
