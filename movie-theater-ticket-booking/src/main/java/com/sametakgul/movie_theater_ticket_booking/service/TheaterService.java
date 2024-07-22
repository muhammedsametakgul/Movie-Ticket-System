package com.sametakgul.movie_theater_ticket_booking.service;

import com.sametakgul.movie_theater_ticket_booking.entity.Theater;
import com.sametakgul.movie_theater_ticket_booking.entity.TheaterSeat;
import com.sametakgul.movie_theater_ticket_booking.enums.SeatType;
import com.sametakgul.movie_theater_ticket_booking.exception.TheaterAlreadyExists;
import com.sametakgul.movie_theater_ticket_booking.mapper.TheaterMapper;
import com.sametakgul.movie_theater_ticket_booking.repository.TheaterRepository;
import com.sametakgul.movie_theater_ticket_booking.request.TheaterRequest;
import com.sametakgul.movie_theater_ticket_booking.request.TheaterSeatRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public String addTheater(TheaterRequest theaterRequest) throws TheaterAlreadyExists {
        if (theaterRepository.findByAddress(theaterRequest.getAddress()) != null) {
            throw new TheaterAlreadyExists();
        }

        Theater theater = TheaterMapper.theaterDtoToTheater(theaterRequest);

        theaterRepository.save(theater);
        return "Theater has been saved Successfully";
    }


    public String addTheaterSeat(TheaterSeatRequest entryDto) throws TheaterAlreadyExists {
        if (theaterRepository.findByAddress(entryDto.getAddress()) == null) {
            throw new TheaterAlreadyExists();
        }

        Integer noOfSeatsInRow = entryDto.getNoOfSeatInRow();
        Integer noOfPremiumSeats = entryDto.getNoOfPremiumSeat();
        Integer noOfClassicSeat = entryDto.getNoOfClassicSeat();
        String address = entryDto.getAddress();

        Theater theater = theaterRepository.findByAddress(address);

        List<TheaterSeat> seatList = theater.getTheaterSeatList();

        int counter = 1;
        int fill = 0;
        char ch = 'A';

        for (int i = 1; i <= noOfClassicSeat; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        for (int i = 1; i <= noOfPremiumSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        theaterRepository.save(theater);

        return "Theater Seats have been added successfully";
    }
}
