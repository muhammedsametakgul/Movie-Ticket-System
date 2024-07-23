package com.sametakgul.movie_theater_ticket_booking.entity.response;

import com.sametakgul.movie_theater_ticket_booking.entity.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatResponse {

    private Integer id;
    private String seatNo;
    private SeatType seatType;
    private Integer price;
    private Boolean isAvailable;
    private Boolean isFoodContains;
}
