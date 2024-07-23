package com.sametakgul.movie_theater_ticket_booking.entity.model;

import com.sametakgul.movie_theater_ticket_booking.entity.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SHOW_SEATS")
@Data
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Integer price;

    private Boolean isAvailable;

    private Boolean isFoodContains;

    @ManyToOne
    @JoinColumn
    private Show show;
}