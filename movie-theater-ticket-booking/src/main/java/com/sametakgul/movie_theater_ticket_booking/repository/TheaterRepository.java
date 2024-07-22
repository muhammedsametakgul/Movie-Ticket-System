package com.sametakgul.movie_theater_ticket_booking.repository;

import com.sametakgul.movie_theater_ticket_booking.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);
}