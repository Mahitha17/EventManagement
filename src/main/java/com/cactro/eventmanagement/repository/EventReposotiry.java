package com.cactro.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cactro.eventmanagement.entity.Events;

public interface EventReposotiry extends JpaRepository<Events,Integer>{
	
	
}

