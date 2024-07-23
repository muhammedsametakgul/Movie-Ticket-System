package com.sametakgul.movie_theater_ticket_booking.service;

import com.sametakgul.movie_theater_ticket_booking.entity.model.Movie;
import com.sametakgul.movie_theater_ticket_booking.exception.MovieAlreadyExist;
import com.sametakgul.movie_theater_ticket_booking.mapper.MovieMapper;
import com.sametakgul.movie_theater_ticket_booking.repository.MovieRepository;
import com.sametakgul.movie_theater_ticket_booking.entity.request.MovieRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String addMovie(MovieRequest movieRequest){
        Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());

        if(movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())){
            throw  new MovieAlreadyExist();
        }
        Movie movie = MovieMapper.movieDtoToMovie(movieRequest);

        movieRepository.save(movie);
        return "The movie has been added successfully";
    }
}
