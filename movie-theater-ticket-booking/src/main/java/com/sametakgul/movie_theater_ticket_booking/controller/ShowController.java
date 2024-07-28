package com.sametakgul.movie_theater_ticket_booking.controller;


import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.request.ShowRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.request.ShowSeatRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.response.ShowResponse;
import com.sametakgul.movie_theater_ticket_booking.service.ShowService;
import com.sametakgul.movie_theater_ticket_booking.utils.MovieTicketResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewShow(@RequestBody ShowRequest showRequest) {
        try {
            String result = showService.addShow(showRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateSeats")
    public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatRequest showSeatRequest) {
        try {
            String result = showService.associateShowSeats(showSeatRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getShows")
    public MovieTicketResponse<List<ShowResponse>> getShows() {
        List<ShowResponse> shows = showService.getAllShows();

        if (shows == null || shows.isEmpty()) {
            return MovieTicketResponse.error("Not Show Found");
        }

        return MovieTicketResponse.success(shows);
    }

}
