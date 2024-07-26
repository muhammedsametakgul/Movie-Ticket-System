package com.sametakgul.movie_theater_ticket_booking.controller;


import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.request.TicketRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.response.TicketResponse;
import com.sametakgul.movie_theater_ticket_booking.service.TicketService;
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
    public ResponseEntity<byte[]> getTicketAsPDF(@PathVariable Integer ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        Show show = ticket.getShow();
        byte[] pdfContent = null;
        HttpHeaders headers = new HttpHeaders();
        String ticketFileName = ticket.getUser().getName() + show.getMovie().getMovieName() + ".pdf";

        try{
             pdfContent = ticketService.getTicketAsPDF(show,ticket);
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", ticketFileName);
        }catch (Exception e){
            System.out.println();
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
