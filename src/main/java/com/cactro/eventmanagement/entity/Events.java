package com.cactro.eventmanagement.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Events {
	@Id
	@SequenceGenerator(
	        name = "event_seq",
	        sequenceName = "event_seq", 
	        allocationSize = 1 
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "event_seq"
	)
	private Integer id;
	private String title;
	private String description;
	private String venue;
	private String eventDate;
	private String totalTickets;
	private String availableTickets;
	@ManyToOne
	@JoinColumn(name = "organizer_id")
	private Users organizerId ;
	private String status;
	@Column(name = "updated_on", updatable = false)
	private Date updatedOn;
	@Column(name = "created_on", nullable = false, updatable = false)
	private Date createdOn;
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
	public String getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(String totalTickets) {
		this.totalTickets = totalTickets;
	}
	public String getAvailableTickets() {
		return availableTickets;
	}
	public void setAvailableTickets(String availableTickets) {
		this.availableTickets = availableTickets;
	}
	public Users getOrganizerId() {
		return organizerId;
	}
	public void setOrganizerId(Users organizerId) {
		this.organizerId = organizerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	

}
