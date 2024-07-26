package com.sametakgul.movie_theater_ticket_booking.controller;

import com.sametakgul.movie_theater_ticket_booking.entity.request.TheaterRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.request.TheaterSeatRequest;
import com.sametakgul.movie_theater_ticket_booking.service.TheaterService;
import com.sametakgul.movie_theater_ticket_booking.utils.MovieTicketResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/addNew")
    public ResponseEntity<String> addTheater(@RequestBody TheaterRequest request) {
        try {
            String result = theaterService.addTheater(request);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTheaterSeat")
    public MovieTicketResponse<TheaterSeatRequest> addTheaterSeat(@RequestBody TheaterSeatRequest entryDto) {
        try {
            String result = theaterService.addTheaterSeat(entryDto);
            return MovieTicketResponse.success(entryDto);
        } catch (Exception e) {
            return MovieTicketResponse.error();
        }
    }
}
