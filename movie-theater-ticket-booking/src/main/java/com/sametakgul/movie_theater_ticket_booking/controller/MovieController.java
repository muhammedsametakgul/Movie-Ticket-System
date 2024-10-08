package com.sametakgul.movie_theater_ticket_booking.controller;


import com.sametakgul.movie_theater_ticket_booking.entity.model.Movie;
import com.sametakgul.movie_theater_ticket_booking.entity.request.MovieRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.response.MovieResponse;
import com.sametakgul.movie_theater_ticket_booking.service.MovieService;
import com.sametakgul.movie_theater_ticket_booking.utils.MovieTicketResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewMovie(@RequestBody MovieRequest movieRequest) {
        try {
            String result = movieService.addMovie(movieRequest);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMovie")
    public MovieTicketResponse<List<MovieResponse>> getAllMovies() {
        return  MovieTicketResponse.success(movieService.getAllMovies());
    }

    @GetMapping("/getMovie/{movieId}")
    public ResponseEntity<MovieTicketResponse<MovieResponse>> getMovieById(@PathVariable int movieId) {
        MovieResponse movieResponse = movieService.getMovieById(movieId);

        if (movieResponse == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MovieTicketResponse.error("Movie not found"));
        }

        return ResponseEntity.ok(MovieTicketResponse.success(movieResponse));
    }

}
