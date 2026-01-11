package com.cactro.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.repository.UsersRepository;


@Service
public class UserService {
	@Autowired
	UsersRepository urep;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public Users saveUser(Users user) {
		if(urep.findByUsername(user.getUsername())==null) {
			user.setPassword(encoder.encode(user.getPassword()));
			return urep.save(user);
		}else {
			return null;
		}
		
	}
	 public UserDetails getCurrentUserDetails() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication == null || !authentication.isAuthenticated()) {
	            return null;
	        }

	        Object principal = authentication.getPrincipal();

	        if (principal instanceof UserDetails) {
	            return (UserDetails) principal; 
	        }

	        return null;
	    }
		
}
