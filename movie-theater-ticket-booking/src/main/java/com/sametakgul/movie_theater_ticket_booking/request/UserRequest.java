package com.sametakgul.movie_theater_ticket_booking.request;

import com.sametakgul.movie_theater_ticket_booking.enums.Gender;
import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private Gender gender;
    private String roles;
}