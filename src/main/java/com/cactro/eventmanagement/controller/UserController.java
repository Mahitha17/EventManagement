package com.cactro.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.service.JwtService;
import com.cactro.eventmanagement.service.UserService;


@RestController
public class UserController {

	@Autowired
    AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;
	@Autowired
	JwtService jwtService;


    UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user) {
		return userService.saveUser(user);
		
	}
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		System.out.println("User in = "+user.getUsername());
		Authentication authenticateion = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authenticateion.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authenticateion.getPrincipal();
			String jwtToken = jwtService.generateToken(userDetails);
			return jwtToken;
		}
		return "Failed to login";
		
	}
	

}
