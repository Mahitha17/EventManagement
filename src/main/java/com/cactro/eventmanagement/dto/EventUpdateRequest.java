package com.cactro.eventmanagement.dto;

public class EventUpdateRequest {

    private String title;
    private String description;
    private String venue;
    private String eventDate;
    private Integer totalTickets;
    private String status;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public Integer getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(Integer totalTickets) {
		this.totalTickets = totalTickets;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    
}
