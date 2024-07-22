package com.sametakgul.movie_theater_ticket_booking.request;
import lombok.Data;

import java.sql.Date;

@Data
public class ShowTimingsRequest {
    private Date date;
    private Integer theaterId;
    private Integer movieId;
}