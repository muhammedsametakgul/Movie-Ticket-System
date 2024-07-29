package com.sametakgul.movie_theater_ticket_booking.utils;

import com.sametakgul.movie_theater_ticket_booking.config.RabbitMQConfig;
import com.sametakgul.movie_theater_ticket_booking.entity.request.EmailSendRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailQueueSender {

    private final RabbitTemplate rabbitTemplate;

    public EmailQueueSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendInfoToEmailQueue(EmailSendRequest email) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, email);
    }
}
