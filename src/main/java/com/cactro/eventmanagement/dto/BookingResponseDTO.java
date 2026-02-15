package com.cactro.eventmanagement.dto;

import java.util.Date;


public class BookingResponseDTO {

    private Integer bookingId;

    private Integer eventId;
    private String eventTitle;
    private String eventDate;
    private String venue;

    private Integer ticketCount;

    private String customerName;

    private Date bookingDate;
    private String status;
    
    
    public BookingResponseDTO() {
    	
    }
    
	public BookingResponseDTO(Integer bookingId, Integer eventId, String eventTitle, String eventDate, String venue,
			Integer ticketCount, String customerName, Date bookingDate, String status) {
		super();
		this.bookingId = bookingId;
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.eventDate = eventDate;
		this.venue = venue;
		this.ticketCount = ticketCount;
		this.customerName = customerName;
		this.bookingDate = bookingDate;
		this.status = status;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public Integer getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(Integer ticketCount) {
		this.ticketCount = ticketCount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
