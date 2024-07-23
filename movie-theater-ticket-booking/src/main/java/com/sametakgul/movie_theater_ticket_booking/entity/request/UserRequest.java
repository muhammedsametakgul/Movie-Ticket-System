package com.sametakgul.movie_theater_ticket_booking.entity.request;

import com.sametakgul.movie_theater_ticket_booking.entity.enums.Gender;
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