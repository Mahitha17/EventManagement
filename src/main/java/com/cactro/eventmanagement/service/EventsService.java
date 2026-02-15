package com.cactro.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import com.cactro.eventmanagement.dto.EventListDTO;
import com.cactro.eventmanagement.dto.EventSaveDTO;
import com.cactro.eventmanagement.dto.EventUpdateRequest;
import com.cactro.eventmanagement.entity.Bookings;
import com.cactro.eventmanagement.entity.Events;
import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.exception.ResourceNotFoundException;
import com.cactro.eventmanagement.repository.EventReposotiry;

@Service
public class EventsService {
	
	@Autowired
	EventReposotiry eventRepo;
	
	@Autowired
	UserService userService;

	@Autowired
	EventEntryNotificationService notificationService;

	public List<EventListDTO> getEventsList() {
		List<Events> events = eventRepo.findAll();

	    return events.stream()
	            .map(this::convertToDTO)
	            .toList();
		
	}
	
	private EventListDTO convertToDTO(Events event) {
	    return new EventListDTO(
	            event.getId(),
	            event.getTitle(),
	            event.getDescription(),
	            event.getVenue(),
	            event.getEventDate(),
	            event.getAvailableTickets(),
	            event.getStatus(),
	            event.getOrganizerId() != null
	                    ? event.getOrganizerId().getUsername()
	                    : null
	    );
	}


	public EventsService(EventReposotiry eventReposotiry, EventEntryNotificationService notificationService) {
		this.eventRepo = eventReposotiry;
		this.notificationService = notificationService;
	}

//	public EventDTO updateEvent(Integer id, EventUpdateRequest event) {
//		if(id!=0 && eventRepo.findById(id).isPresent()) {
//			Events old_event = eventRepo.findById(id).get();
//			old_event.setDescription(event.getDescription());
//			old_event.setEventDate(event.getEventDate());
//			if (userService.getCurrentUserDetails() instanceof UserDetailsImpl userImpl) {
//				old_event.setOrganizerId(userImpl.user);
//	        }
//			old_event.setStatus(event.getStatus());
//			old_event.setTitle(old_event.getTitle());
//			old_event.setTotalTickets(event.getTotalTickets());
//			old_event.setUpdatedOn(new Date(System.currentTimeMillis()));
//			old_event.setVenue(event.getVenue());
//			Events saveEvent = eventRepo.save(old_event);
//			return convertToDTO(saveEvent);
//		}
//		else {
//			return new ResourceNotFoundException("Event not found");
//		}
//	}
	public EventListDTO updateEvent(Integer id, EventUpdateRequest event) {

	    Events oldEvent = eventRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

	    oldEvent.setDescription(event.getDescription());
	    oldEvent.setEventDate(event.getEventDate());

	    if (userService.getCurrentUserDetails() instanceof UserDetailsImpl userImpl) {
	        oldEvent.setOrganizerId(userImpl.user);
	    }

	    oldEvent.setStatus(event.getStatus());
	    oldEvent.setTitle(event.getTitle());
	    oldEvent.setTotalTickets(event.getTotalTickets());
	    oldEvent.setUpdatedOn(new Date());
	    oldEvent.setVenue(event.getVenue());

	    Events savedEvent = eventRepo.save(oldEvent);

	    return convertToDTO(savedEvent);
	}
	public Events saveEvent(EventSaveDTO eventdto) {
		Events event = new Events();
		event.setDescription(eventdto.getDescription());
		event.setEventDate(eventdto.getEventDate());
		event.setVenue(eventdto.getVenue());
		event.setStatus("Active");
		event.setTitle(eventdto.getTitle());
		event.setTotalTickets(eventdto.getTotalTickets());
		event.setAvailableTickets(eventdto.getTotalTickets());
		
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
