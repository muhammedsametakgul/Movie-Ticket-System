package com.sametakgul.movie_theater_ticket_booking.request;

import com.sametakgul.movie_theater_ticket_booking.enums.Genre;
import com.sametakgul.movie_theater_ticket_booking.enums.Language;
import lombok.Data;

import java.util.Date;

@Data
public class MovieRequest {
    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}