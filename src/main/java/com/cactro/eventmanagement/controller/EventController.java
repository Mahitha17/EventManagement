package com.cactro.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cactro.eventmanagement.entity.Events;
import com.cactro.eventmanagement.service.EventsService;

@RestController
public class EventController {
	
	@Autowired
	EventsService eventService;
	
	
//	@PreAuthorize("hasRole('EVENT ORGANIZERS')")
	@GetMapping("/events")
	public List<Events> getEventsList() {
		return eventService.getEventsList();
	}
	
	@PreAuthorize("hasRole('EVENT ORGANIZER')")
	@PutMapping("/event/{id}")
	public Events updateEvent(@PathVariable("id") Integer id,@RequestBody Events event) {
		return eventService.updateEvent(id,event);
	}
	
	@PreAuthorize("hasRole('EVENT ORGANIZER')")
	@PostMapping("/events/saveEvent")
	public Events saveEvent(@RequestBody Events event) {
		return eventService.saveEvent(event);
	}
	
	

}
