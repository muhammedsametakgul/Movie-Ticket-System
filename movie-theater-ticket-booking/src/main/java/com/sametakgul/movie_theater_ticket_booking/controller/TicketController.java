package com.sametakgul.movie_theater_ticket_booking.controller;


import com.sametakgul.movie_theater_ticket_booking.request.TicketRequest;
import com.sametakgul.movie_theater_ticket_booking.response.TicketResponse;
import com.sametakgul.movie_theater_ticket_booking.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/book")
    public ResponseEntity<Object> ticketBooking(@RequestBody TicketRequest ticketRequest) {
        try {
            TicketResponse result = ticketService.ticketBooking(ticketRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
