package com.cactro.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cactro.eventmanagement.dto.EventListDTO;
import com.cactro.eventmanagement.dto.EventSaveDTO;
import com.cactro.eventmanagement.dto.EventUpdateRequest;
import com.cactro.eventmanagement.entity.Events;
import com.cactro.eventmanagement.service.EventsService;

@RestController
public class EventController {
	
	@Autowired
	EventsService eventService;
	
	
	@GetMapping("/events")
	public ResponseEntity< List<EventListDTO>> getEventsList() {
		return ResponseEntity.ok(eventService.getEventsList());
	}
	

	@PreAuthorize("hasRole('EVENT_ORGANIZER')")
	@PutMapping("/events/{id}")
	public ResponseEntity<EventListDTO> updateEvent(
	        @PathVariable Integer id,
	        @RequestBody EventUpdateRequest request) {

	    EventListDTO updatedEvent = eventService.updateEvent(id, request);
	    return ResponseEntity.ok(updatedEvent);
	   
	}
	
	@PreAuthorize("hasRole('EVENT ORGANIZER')")
	@PostMapping("/events/saveEvent")
	public ResponseEntity<String> saveEvent(@RequestBody EventSaveDTO event) {
		eventService.saveEvent(event);
		return ResponseEntity.ok("Saved successfully!");
	}
	
	

}
