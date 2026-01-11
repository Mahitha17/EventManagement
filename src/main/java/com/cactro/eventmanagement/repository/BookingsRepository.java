package com.cactro.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cactro.eventmanagement.entity.Bookings;
import com.cactro.eventmanagement.entity.Events;

public interface BookingsRepository extends JpaRepository<Bookings,Integer>{
	@Query("SELECT COALESCE(SUM(b.ticketCount), 0) FROM Bookings b WHERE b.eventId = :event")
	int sumTicketsByEvent(@Param("event") Events event);

}
