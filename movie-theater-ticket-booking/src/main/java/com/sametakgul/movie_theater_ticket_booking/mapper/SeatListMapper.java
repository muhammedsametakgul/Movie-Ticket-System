package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.model.ShowSeat;
import com.sametakgul.movie_theater_ticket_booking.entity.response.ShowSeatResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatListMapper {

    public static ShowSeatResponse showSeatToShowSeatResponse(ShowSeat showSeat) {
        if (showSeat == null) {
            return null;
        }

        ShowSeatResponse showSeatResponse = ShowSeatResponse.builder()
                .id(showSeat.getId())
                .seatNo(showSeat.getSeatNo())
                .seatType(showSeat.getSeatType())
                .price(showSeat.getPrice())
                .isAvailable(showSeat.getIsAvailable())
                .isFoodContains(showSeat.getIsFoodContains())
                .build();

        return showSeatResponse;
    }

    public static List<ShowSeatResponse> showSeatListToShowSeatResponseList(List<ShowSeat> showSeatList) {
        if (showSeatList == null || showSeatList.isEmpty()) {
            return new ArrayList<>();
        }

        return showSeatList.stream()
                .map(SeatListMapper::showSeatToShowSeatResponse)
                .collect(Collectors.toList());
    }
}
