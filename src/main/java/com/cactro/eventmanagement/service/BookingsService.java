package com.cactro.eventmanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cactro.eventmanagement.dto.BookingRequest;
import com.cactro.eventmanagement.dto.BookingResponseDTO;
import com.cactro.eventmanagement.entity.Bookings;
import com.cactro.eventmanagement.entity.Events;
import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.exception.BadRequestException;
import com.cactro.eventmanagement.exception.ResourceNotFoundException;
import com.cactro.eventmanagement.repository.BookingsRepository;
import com.cactro.eventmanagement.repository.EventReposotiry;

@Service
public class BookingsService {
	@Autowired
	BookingsRepository bookingRepo;
	@Autowired
	EventReposotiry eventRepo;
	@Autowired
	BookingNotificationService notificationService;
	@Autowired
	UserService userService;

	public List<BookingResponseDTO> getAllBookings() {
		List<Bookings> bookings = bookingRepo.findAll();
		return bookings.stream()
	            .map(this::convertToDTO)
	            .toList();
	}
	private BookingResponseDTO convertToDTO(Bookings booking) {

	    BookingResponseDTO dto = new BookingResponseDTO();

	    dto.setBookingId(booking.getId());
	    dto.setTicketCount(booking.getTicketCount());
	    dto.setBookingDate(booking.getCreatedOn());
	    dto.setStatus(booking.getStatus());

	    dto.setEventId(booking.getEventId().getId());
	    dto.setEventTitle(booking.getEventId().getTitle());
	    dto.setEventDate(booking.getEventId().getEventDate());
	    dto.setVenue(booking.getEventId().getVenue());

	    dto.setCustomerName(booking.getCustomerId().getUsername());

	    return dto;
	}
	public BookingsService(BookingsRepository bookingRepository, BookingNotificationService notificationService) {
		this.bookingRepo = bookingRepository;
		this.notificationService = notificationService;
	}


	
	@Transactional
	public BookingResponseDTO createBooking(BookingRequest request) {

	    Events event = eventRepo.findById(request.getEventId())
	            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

	    if (event.getAvailableTickets() < request.getTicketCount()) {
	        throw new BadRequestException("Not enough tickets available");
	    }

	    event.setAvailableTickets(
	            event.getAvailableTickets() - request.getTicketCount()
	    );

	    Bookings booking = new Bookings();
	    booking.setEventId(event);
	    booking.setTicketCount(request.getTicketCount());
	    booking.setCreatedOn(new Date(System.currentTimeMillis()));
	    booking.setStatus("Active");
	    Users user = null;
		if (userService.getCurrentUserDetails() instanceof UserDetailsImpl userImpl) {
			booking.setCustomerId(userImpl.user);
			user = userImpl.user;
        }

	    Bookings savedBooking = bookingRepo.save(booking);
		notificationService.sendBookingConfirmation(user.getEmail(), savedBooking.getId());

	    return convertToDTO(savedBooking);
	}
}
