package com.sametakgul.movie_theater_ticket_booking.utils;

import com.sametakgul.movie_theater_ticket_booking.config.RabbitMQConfig;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Ticket;
import com.sametakgul.movie_theater_ticket_booking.entity.request.EmailSendRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailQueueSender {

    private final RabbitTemplate rabbitTemplate;

    public EmailQueueSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendInfoToEmailQueue(Ticket ticket) {
        EmailSendRequest emailSendRequest = EmailSendRequest.builder().
                email(ticket.getUser().getEmailId())
                        .message(generateMessage(ticket))
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, emailSendRequest);
    }

    private String generateMessage(Ticket ticket){
        return "Sayın " + ticket.getUser().getName() + " " + ticket.getShow().getMovie().getMovieName()
                +" isimli film için " + ticket.getBookedSeats()+ "numaralı koltuklarda biletiniz kesilmiştir." + " İyi seyirler!";
    }
}
