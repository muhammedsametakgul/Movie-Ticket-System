package com.sametakgul.movie_theater_ticket_booking.entity;


import com.sametakgul.movie_theater_ticket_booking.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THEATER_SEATS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theater theater;
}