package com.sametakgul.movie_theater_ticket_booking.service;

import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.model.ShowSeat;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.auth.entity.model.User;
import com.sametakgul.movie_theater_ticket_booking.entity.request.EmailSendRequest;
import com.sametakgul.movie_theater_ticket_booking.exception.SeatsNotAvailable;
import com.sametakgul.movie_theater_ticket_booking.exception.ShowDoesNotExist;
import com.sametakgul.movie_theater_ticket_booking.auth.util.exception.UserDoesNotExist;
import com.sametakgul.movie_theater_ticket_booking.mapper.TicketMapper;
import com.sametakgul.movie_theater_ticket_booking.repository.ShowRepository;
import com.sametakgul.movie_theater_ticket_booking.repository.TicketRepository;
import com.sametakgul.movie_theater_ticket_booking.auth.repository.UserRepository;
import com.sametakgul.movie_theater_ticket_booking.entity.request.TicketRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.response.TicketResponse;
import com.sametakgul.movie_theater_ticket_booking.utils.EmailQueueSender;
import com.sametakgul.movie_theater_ticket_booking.utils.PdfUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final EmailQueueSender emailQueueSender;

    public TicketService(TicketRepository ticketRepository, ShowRepository showRepository, UserRepository userRepository, EmailQueueSender emailQueueSender) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.emailQueueSender = emailQueueSender;
    }

    public TicketResponse ticketBooking(TicketRequest ticketRequest) {
        Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());

        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExist();
        }

        Optional<User> userOpt = userRepository.findById(ticketRequest.getUserId());

        if (userOpt.isEmpty()) {
            throw new UserDoesNotExist();
        }

        User user = userOpt.get();
        Show show = showOpt.get();

        Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(), ticketRequest.getRequestSeats());

        if (!isSeatAvailable) {
            throw new SeatsNotAvailable();
        }

        // count price
        Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeatList(), ticketRequest.getRequestSeats());

        String seats = listToString(ticketRequest.getRequestSeats());

        Ticket ticket = new Ticket();
        ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
        ticket.setBookedSeats(seats);
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);
        userRepository.save(user);
        showRepository.save(show);

        sendEmail(user,ticket);
        return TicketMapper.returnTicket( ticket);
    }

    private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
        for (ShowSeat showSeat : showSeatList) {
            String seatNo = showSeat.getSeatNo();

            if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
                return false;
            }
        }

        return true;
    }

    private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
        Integer totalAmount = 0;

        for (ShowSeat showSeat : showSeatList) {
            if (requestSeats.contains(showSeat.getSeatNo())) {
                totalAmount += showSeat.getPrice();
                showSeat.setIsAvailable(Boolean.FALSE);
            }
        }

        return totalAmount;
    }

    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();

        for (String s : requestSeats) {
            sb.append(s).append(",");
        }

        return sb.toString();
    }
    public byte[] getTicketAsPDF(Ticket ticket) {
        if ( ticket == null) {
            throw new IllegalArgumentException("Show and Ticket must not be null");
        }

        TicketResponse ticketResponse = TicketMapper.returnTicket( ticket);
        return PdfUtils.createTicketPdf(ticketResponse);
    }


    public Ticket getTicketById(int id){
        return ticketRepository.findById(id).orElse(null);
    }

    public void sendEmail(User user, Ticket ticket){

        EmailSendRequest emailSendRequest = EmailSendRequest.builder().
                email(user.getEmailId())
                .message(generateMessage(ticket))
                .build();

        emailQueueSender.sendInfoToEmailQueue(emailSendRequest);
    }

    private String generateMessage(Ticket ticket){
        return "Sayın " + ticket.getUser().getName() + " " + ticket.getShow().getMovie().getMovieName()
                +" isimli film için " + ticket.getBookedSeats()+ "numaralı koltuklarda biletiniz kesilmiştir." + " İyi seyirler!";
    }
}
