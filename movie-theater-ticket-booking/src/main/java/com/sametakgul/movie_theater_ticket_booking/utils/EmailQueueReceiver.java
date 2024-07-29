package com.sametakgul.movie_theater_ticket_booking.utils;

import com.sametakgul.movie_theater_ticket_booking.config.RabbitMQConfig;
import com.sametakgul.movie_theater_ticket_booking.entity.request.EmailSendRequest;
import com.sametakgul.movie_theater_ticket_booking.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailQueueReceiver {

    private final EmailService emailService;

    public EmailQueueReceiver(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receiveLog(EmailSendRequest email) {
        emailService.sendEmail(email);
    }
}
