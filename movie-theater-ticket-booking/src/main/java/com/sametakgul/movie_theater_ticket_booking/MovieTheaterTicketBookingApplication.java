package com.sametakgul.movie_theater_ticket_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MovieTheaterTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTheaterTicketBookingApplication.class, args);
	}

}
