package com.sametakgul.movie_theater_ticket_booking.repository;


import com.sametakgul.movie_theater_ticket_booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}