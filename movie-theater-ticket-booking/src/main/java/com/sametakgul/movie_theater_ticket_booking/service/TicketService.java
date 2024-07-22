package com.sametakgul.movie_theater_ticket_booking.service;

import com.sametakgul.movie_theater_ticket_booking.entity.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.ShowSeat;
import com.sametakgul.movie_theater_ticket_booking.entity.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.User;
import com.sametakgul.movie_theater_ticket_booking.exception.SeatsNotAvailable;
import com.sametakgul.movie_theater_ticket_booking.exception.ShowDoesNotExist;
import com.sametakgul.movie_theater_ticket_booking.exception.UserDoesNotExist;
import com.sametakgul.movie_theater_ticket_booking.mapper.TicketMapper;
import com.sametakgul.movie_theater_ticket_booking.repository.ShowRepository;
import com.sametakgul.movie_theater_ticket_booking.repository.TicketRepository;
import com.sametakgul.movie_theater_ticket_booking.repository.UserRepository;
import com.sametakgul.movie_theater_ticket_booking.request.TicketRequest;
import com.sametakgul.movie_theater_ticket_booking.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final ShowRepository showRepository;

    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, ShowRepository showRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
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
        Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeatList(),	ticketRequest.getRequestSeats());

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

        return TicketMapper.returnTicket(show, ticket);
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

}