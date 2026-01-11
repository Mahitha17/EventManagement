package com.cactro.eventmanagement.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cactro.eventmanagement.entity.Users;
import com.cactro.eventmanagement.repository.UsersRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UsersRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUsername(username);
		return new UserDetailsImpl(user);
	}
	

}
