package com.cactro.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	public ResponseEntity<String> login(@RequestBody Users user) {
		System.out.println("User in = "+user.getUsername());
		try {
	        Authentication authentication =
	            authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                    user.getUsername(),
	                    user.getPassword()
	                )
	            );

	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String jwtToken = jwtService.generateToken(userDetails);
	        return ResponseEntity.ok(jwtToken);
		} catch (BadCredentialsException e) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body("Invalid username or password");

	    } catch (LockedException e) {
	        return ResponseEntity
	                .status(HttpStatus.LOCKED)
	                .body("Account is locked");

	    } catch (UsernameNotFoundException e) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body("User not found");
	    }
		
	}
	

}
