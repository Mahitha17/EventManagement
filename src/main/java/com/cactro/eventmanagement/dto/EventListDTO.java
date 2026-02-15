package com.cactro.eventmanagement.dto;

public class EventListDTO {

    private Integer id;
    private String title;
    private String description;
    private String venue;
    private String eventDate;
    private Integer availableTickets;
    private String status;
    private String organizerName;

    public EventListDTO(Integer id,
                    String title,
                    String description,
                    String venue,
                    String eventDate,
                    Integer availableTickets,
                    String status,
                    String organizerName) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.eventDate = eventDate;
        this.availableTickets = availableTickets;
        this.status = status;
        this.organizerName = organizerName;
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

	public Integer getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(Integer availableTickets) {
		this.availableTickets = availableTickets;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

    
}
