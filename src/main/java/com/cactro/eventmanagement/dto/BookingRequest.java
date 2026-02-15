package com.cactro.eventmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookingRequest {
	@NotNull(message = "Event ID is required")
	private Integer eventId;
	@Min(value = 1, message = "At least 1 ticket must be booked")
	private int ticketCount;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

}
