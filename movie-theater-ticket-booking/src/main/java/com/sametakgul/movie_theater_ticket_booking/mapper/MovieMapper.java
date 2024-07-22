package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.Movie;
import com.sametakgul.movie_theater_ticket_booking.request.MovieRequest;

public class MovieMapper {

    public static Movie movieDtoToMovie(MovieRequest movieRequest){
        Movie movie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .duration(movieRequest.getDuration())
                .genre(movieRequest.getGenre())
                .language(movieRequest.getLanguage())
                .releaseDate(movieRequest.getReleaseDate())
                .rating(movieRequest.getRating())
                .build();

        return movie;
    }
}
