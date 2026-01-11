package com.cactro.eventmanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cactro.eventmanagement.entity.Bookings;
import com.cactro.eventmanagement.entity.Events;
import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.repository.BookingsRepository;

@Service
public class BookingsService {
	@Autowired
	BookingsRepository bookingRepo;
	@Autowired
	BookingNotificationService notificationService;
	@Autowired
	UserService userService;

	public List<Bookings> getAllBookings() {
		return bookingRepo.findAll();
	}

	public BookingsService(BookingsRepository bookingRepository, BookingNotificationService notificationService) {
		this.bookingRepo = bookingRepository;
		this.notificationService = notificationService;
	}

	@Transactional
	public Bookings createBooking(Events event, int ticketCount) {
		Bookings booking = new Bookings();
		booking.setEventId(event);
		
		 int bookedTickets = bookingRepo.sumTicketsByEvent(event);

	        if (bookedTickets + ticketCount > event.getTotalTickets()) {
	            throw new RuntimeException("Not enough tickets available");
	        }
	        
		Users user = null;
		if (userService.getCurrentUserDetails() instanceof UserDetailsImpl userImpl) {
			booking.setCustomerId(userImpl.user);
			user = userImpl.user;
        }
		booking.setTicketCount(ticketCount);
		booking.setCreatedOn(new Date(System.currentTimeMillis()));

		Bookings savedBooking = bookingRepo.save(booking);
		 notificationService.sendBookingConfirmation(user.getEmail(), savedBooking.getId());
		return savedBooking;

	}
}
