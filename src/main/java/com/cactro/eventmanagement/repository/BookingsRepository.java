package com.cactro.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cactro.eventmanagement.entity.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings,Integer>{

}
