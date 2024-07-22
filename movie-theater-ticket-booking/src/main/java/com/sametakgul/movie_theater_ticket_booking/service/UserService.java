package com.sametakgul.movie_theater_ticket_booking.service;


import com.sametakgul.movie_theater_ticket_booking.entity.User;
import com.sametakgul.movie_theater_ticket_booking.exception.UserExists;
import com.sametakgul.movie_theater_ticket_booking.mapper.UserMapper;
import com.sametakgul.movie_theater_ticket_booking.repository.UserRepository;
import com.sametakgul.movie_theater_ticket_booking.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String addUser(UserRequest userRequest) {
        Optional<User> users = userRepository.findByEmailId(userRequest.getEmailId());

        if (users.isPresent()) {
            throw new UserExists();
        }

        User user = UserMapper.userDtoToUser(userRequest );

        userRepository.save(user);
        return "User Saved Successfully";
    }

}