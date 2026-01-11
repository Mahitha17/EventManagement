package com.cactro.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cactro.eventmanagement.dto.BookingRequest;
import com.cactro.eventmanagement.entity.Bookings;
import com.cactro.eventmanagement.entity.Events;
import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.repository.EventReposotiry;
import com.cactro.eventmanagement.repository.UsersRepository;
import com.cactro.eventmanagement.service.BookingsService;

@RestController
public class BookingController {
	@Autowired
	BookingsService bookingService;
	@Autowired 
	EventReposotiry eventRepo;
	@Autowired 
	UsersRepository userRepository;
	
	@GetMapping("/bookings")
	public List<Bookings> getAllBookings(){
		return bookingService.getAllBookings();
	}
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/bookings/book")
	public Bookings bookAnEvent(@RequestBody BookingRequest bookingRequest) {
		  Events event = eventRepo.findById(bookingRequest.getEventId()).get();

	        Bookings booking = bookingService.createBooking(event, bookingRequest.getTicketCount());
	        return booking;
		
	}

}
