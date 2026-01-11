package com.cactro.eventmanagement.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BookingNotificationService {

    @Async
    public void sendBookingConfirmation(String email, Integer bookingId) {
        System.out.println("Sending booking confirmation to " + email +
                           " for booking ID: " + bookingId);
    }
}
