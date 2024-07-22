package com.sametakgul.movie_theater_ticket_booking.repository;

import com.sametakgul.movie_theater_ticket_booking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieName(String movieName);
}
