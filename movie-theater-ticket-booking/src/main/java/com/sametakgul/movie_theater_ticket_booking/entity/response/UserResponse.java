package com.sametakgul.movie_theater_ticket_booking.entity.response;


import com.sametakgul.movie_theater_ticket_booking.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String name;
    private Integer age;
    private Gender gender;
    private String address;
}
