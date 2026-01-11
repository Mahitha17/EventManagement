package com.cactro.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import com.cactro.eventmanagement.entity.Bookings;
import com.cactro.eventmanagement.entity.Events;
import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.repository.EventReposotiry;

@Service
public class EventsService {
	
	@Autowired
	EventReposotiry eventRepo;
	
	@Autowired
	UserService userService;

	public List<Events> getEventsList() {
		return eventRepo.findAll();
		
	}
	@Autowired
	EventEntryNotificationService notificationService;


	public EventsService(EventReposotiry eventReposotiry, EventEntryNotificationService notificationService) {
		this.eventRepo = eventReposotiry;
		this.notificationService = notificationService;
	}

	public Events updateEvent(Integer id, Events event) {
		if(id!=0 && eventRepo.findById(id).isPresent()) {
			Events old_event = eventRepo.findById(id).get();
			old_event.setAvailableTickets(event.getAvailableTickets());
			old_event.setDescription(event.getDescription());
			old_event.setEventDate(event.getEventDate());
			if (userService.getCurrentUserDetails() instanceof UserDetailsImpl userImpl) {
				old_event.setOrganizerId(userImpl.user);
	        }
			old_event.setStatus(event.getStatus());
			old_event.setTitle(old_event.getTitle());
			old_event.setTotalTickets(event.getTotalTickets());
			old_event.setUpdatedOn(new Date(System.currentTimeMillis()));
			old_event.setVenue(event.getVenue());
			eventRepo.save(old_event);
		}
		return null;
	}
	public Events saveEvent(Events event) {
		event.setCreatedOn(new Date(System.currentTimeMillis()));
		event.setUpdatedOn(new Date(System.currentTimeMillis()));
		userService.getCurrentUserDetails();
		Users user = null;
		if (userService.getCurrentUserDetails() instanceof UserDetailsImpl userImpl) {
			event.setOrganizerId(userImpl.user);
			user = userImpl.user;
        }
		Events savedEntity =eventRepo.save(event);
		 notificationService.sendEventConfirmation(user.getEmail(), savedEntity.getId());
		return savedEntity;
	}
	
	

}
