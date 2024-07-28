package com.sametakgul.movie_theater_ticket_booking.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MovieTicketResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> MovieTicketResponse<T> empty() {
        return success(null);
    }

    public static <T> MovieTicketResponse<T> success(T data) {
        return MovieTicketResponse.<T>builder()
                .message("SUCCESS!")
                .data(data)
                .success(true)
                .build();
    }

    public static <T> MovieTicketResponse<T> error(String message) {
        return MovieTicketResponse.<T>builder()
                .message(message)
                .success(false)
                .build();
    }
}
