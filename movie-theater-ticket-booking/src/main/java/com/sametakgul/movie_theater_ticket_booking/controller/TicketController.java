package com.sametakgul.movie_theater_ticket_booking.controller;


import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.request.TicketRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.response.TicketResponse;
import com.sametakgul.movie_theater_ticket_booking.service.TicketService;
import com.sametakgul.movie_theater_ticket_booking.utils.MovieTicketResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{ticketId}/pdf")
    public ResponseEntity<?> getTicketAsPDF(@PathVariable Integer ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);

        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MovieTicketResponse.error("Ticket not found"));
        }

        Show show = ticket.getShow();
        byte[] pdfContent;
        HttpHeaders headers = new HttpHeaders();
        String ticketFileName = ticket.getUser().getName() + "_" + show.getMovie().getMovieName() + ".pdf";

        try {
            pdfContent = ticketService.getTicketAsPDF( ticket);

            if (pdfContent == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(MovieTicketResponse.error("Error generating PDF"));
            }

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", ticketFileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MovieTicketResponse.error("Error generating PDF"));
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }

}
