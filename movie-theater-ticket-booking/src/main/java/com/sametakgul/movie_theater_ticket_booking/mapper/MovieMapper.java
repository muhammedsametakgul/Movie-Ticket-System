package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.model.Movie;
import com.sametakgul.movie_theater_ticket_booking.entity.request.MovieRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.response.MovieResponse;

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

    public static MovieResponse movieToMovieResponse(Movie movie){
        MovieResponse movieResponse = MovieResponse.builder()
                .movieName(movie.getMovieName())
                .duration(movie.getDuration())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .genre(movie.getGenre())
                .language(movie.getLanguage())
                .build()
                ;

        return  movieResponse;
    }
}
