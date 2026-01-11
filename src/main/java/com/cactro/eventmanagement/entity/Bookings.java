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
public class Bookings {
	@Id
	@SequenceGenerator(
	        name = "booking_seq",
	        sequenceName = "booking_seq", 
	        allocationSize = 1 
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "booking_seq"
	)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Events  eventId;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Users customerId;
	private Integer ticketCount;
	private String status;
	@Column(name = "created_on", nullable = false, updatable = false)
	private Date createdOn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Events getEventId() {
		return eventId;
	}
	public void setEventId(Events eventId) {
		this.eventId = eventId;
	}
	public Users getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Users customerId) {
		this.customerId = customerId;
	}
	public Integer getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(Integer ticketCount) {
		this.ticketCount = ticketCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	


}
