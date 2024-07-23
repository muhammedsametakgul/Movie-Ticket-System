package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.request.ShowRequest;
import com.sametakgul.movie_theater_ticket_booking.entity.response.ShowResponse;

public class ShowMapper {
    public static Show showDtoToShow(ShowRequest showRequest) {
        Show show = Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();

        return show;
    }

    public static ShowResponse showToShowResponse(Show show) {
        ShowResponse showResponse = ShowResponse.builder()
                .showId(show.getShowId())
                .time(show.getTime())
                .date(show.getDate())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getName())
                .theaterAddress(show.getTheater().getAddress())
                .showSeatList(show.getShowSeatList())
                .ticketList(show.getTicketList())
                .build();

        return  showResponse;
    }

}
