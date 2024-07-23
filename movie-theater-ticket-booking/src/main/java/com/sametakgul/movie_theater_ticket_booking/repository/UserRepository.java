package com.sametakgul.movie_theater_ticket_booking.repository;

import java.util.Optional;

import com.sametakgul.movie_theater_ticket_booking.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailId(String emailId);;
}