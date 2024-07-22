package com.sametakgul.movie_theater_ticket_booking.mapper;

import com.sametakgul.movie_theater_ticket_booking.entity.User;
import com.sametakgul.movie_theater_ticket_booking.request.UserRequest;

public class UserMapper {

    public static User userDtoToUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .address(userRequest.getAddress())
                .gender(userRequest.getGender())
                .mobileNo(userRequest.getMobileNo())
                .emailId(userRequest.getEmailId())
                .roles(userRequest.getRoles())
                .password("1234")
                .build();

        return user;
    }


}
