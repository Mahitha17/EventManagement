package com.cactro.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cactro.eventmanagement.entity.Users;


public interface UsersRepository extends JpaRepository<Users,Integer>{
	Users findByUsername(String username);
	
}
