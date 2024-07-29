package com.sametakgul.movie_theater_ticket_booking.service;

import com.sametakgul.movie_theater_ticket_booking.auth.entity.model.User;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.request.EmailSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(EmailSendRequest email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getEmail());
        mailMessage.setSubject("Movie Ticket Booking Confirmation");
        mailMessage.setText(email.getMessage());

        try {
            emailSender.send(mailMessage);
        } catch (Exception e) {
            System.err.println("Failed to send email to " + email.getEmail());
            e.printStackTrace();
        }
    }


}
