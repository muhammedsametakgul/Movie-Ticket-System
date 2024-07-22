package com.sametakgul.movie_theater_ticket_booking.controller;


import com.sametakgul.movie_theater_ticket_booking.request.UserRequest;
import com.sametakgul.movie_theater_ticket_booking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewUser(@RequestBody UserRequest userEntryDto) {
        try {
            String result = userService.addUser(userEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
