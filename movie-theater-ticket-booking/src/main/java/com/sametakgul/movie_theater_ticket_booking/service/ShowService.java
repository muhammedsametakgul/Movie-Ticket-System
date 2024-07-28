package com.sametakgul.movie_theater_ticket_booking.service;


import com.sametakgul.movie_theater_ticket_booking.entity.model.*;
import com.sametakgul.movie_theater_ticket_booking.entity.enums.SeatType;
import com.sametakgul.movie_theater_ticket_booking.entity.response.ShowResponse;
import com.sametakgul.movie_theater_ticket_booking.exception.MovieDoesNotExist;
import com.sametakgul.movie_theater_ticket_booking.exception.ShowDoesNotExist;
import com.sametakgul.movie_theater_ticket_booking.exception.TheaterDoesNotExist;
import com.sametakgul.movie_theater_ticket_booking.mapper.MovieMapper;
import com.sametakgul.movie_theater_ticket_booking.mapper.ShowMapper;
import com.sametakgul.movie_theater_ticket_booking.repository.MovieRepository;
import com.sametakgul.movie_theater_ticket_booking.repository.ShowRepository;
import com.sametakgul.movie_theater_ticket_booking.repository.TheaterRepository;
import com.sametakgul.movie_theater_ticket_booking.entity.request.ShowRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.request.ShowSeatRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;

    public ShowService(MovieRepository movieRepository, TheaterRepository theaterRepository, ShowRepository showRepository) {
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showRepository = showRepository;
    }

    public String addShow(ShowRequest showRequest){
        Show show = ShowMapper.showDtoToShow(showRequest);

        Optional<Movie> movieOpt = movieRepository.findById(showRequest.getMovieId());
        if(movieOpt.isEmpty()){
            throw new MovieDoesNotExist();
        }

        Optional<Theater> theaterOpt = theaterRepository.findById(showRequest.getTheaterId());
        if(theaterOpt.isEmpty()){
            throw new TheaterDoesNotExist();

        }

        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();

        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepository.save(show);

        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added Successfully";
    }

    public String associateShowSeats(ShowSeatRequest showSeatRequest) throws ShowDoesNotExist {
        Optional<Show> showOpt = showRepository.findById(showSeatRequest.getShowId());

        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExist();
        }

        Show show = showOpt.get();
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for (TheaterSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if (showSeat.getSeatType().equals(SeatType.CLASSIC)) {
                showSeat.setPrice((showSeatRequest.getPriceOfClassicSeat()));
            } else {
                showSeat.setPrice(showSeatRequest.getPriceOfPremiumSeat());
            }

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            showSeat.setIsFoodContains(Boolean.FALSE);

            showSeatList.add(showSeat);
        }

        showRepository.save(show);

        return "Show seats have been associated successfully";
    }

    @Cacheable(value = "shows", unless = "#result == null")
    public List<ShowResponse> getAllShows(){
        List<Show> showList = showRepository.findAll();

        return showList.stream()
                .map(ShowMapper::showToShowResponse)
                .collect(Collectors.toList());
    }
}
