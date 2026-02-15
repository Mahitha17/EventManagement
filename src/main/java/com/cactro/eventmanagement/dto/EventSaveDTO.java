package com.cactro.eventmanagement.dto;



public class EventSaveDTO {
	private Integer id;
	private String title;
	private String description;
	private String venue;
	private String eventDate;
	private Integer totalTickets;
	private String status;
	
	public EventSaveDTO(Integer id, String title, String description, String venue, String eventDate,
			Integer totalTickets, String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.venue = venue;
		this.eventDate = eventDate;
		this.totalTickets = totalTickets;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
