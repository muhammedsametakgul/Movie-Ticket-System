package com.sametakgul.movie_theater_ticket_booking.entity.response;

import com.sametakgul.movie_theater_ticket_booking.entity.enums.Genre;
import com.sametakgul.movie_theater_ticket_booking.entity.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Integer id;
    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
