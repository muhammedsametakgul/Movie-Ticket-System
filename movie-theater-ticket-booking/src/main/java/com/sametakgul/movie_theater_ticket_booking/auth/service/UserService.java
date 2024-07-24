package com.sametakgul.movie_theater_ticket_booking.auth.service;


import com.sametakgul.movie_theater_ticket_booking.auth.entity.model.User;
import com.sametakgul.movie_theater_ticket_booking.auth.util.exception.UserExists;
import com.sametakgul.movie_theater_ticket_booking.auth.entity.mapper.UserMapper;
import com.sametakgul.movie_theater_ticket_booking.auth.repository.UserRepository;
import com.sametakgul.movie_theater_ticket_booking.auth.entity.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserRequest userRequest) {
        Optional<User> users = userRepository.findByEmailId(userRequest.getEmailId());

        if (users.isPresent()) {
            throw new UserExists();
        }

        User user = UserMapper.userDtoToUser(userRequest,  passwordEncoder.encode("1234"));

        userRepository.save(user);
        return "User Saved Successfully";
    }
}