package com.sametakgul.movie_theater_ticket_booking.utils;

import com.sametakgul.movie_theater_ticket_booking.config.RabbitMQConfig;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.request.EmailSendRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusQueueReceiver {

    private final EmailQueueSender emailQueueSender;

    public StatusQueueReceiver(EmailQueueSender emailQueueSender) {
        this.emailQueueSender = emailQueueSender;
    }

    @RabbitListener(queues = RabbitMQConfig.STATUS_QUEUE)
    public void receiveStatus(String status, Ticket ticket) {
        if ("SUCCESS".equals(status)) {
            emailQueueSender.sendInfoToEmailQueue(ticket);
        }
    }
}
