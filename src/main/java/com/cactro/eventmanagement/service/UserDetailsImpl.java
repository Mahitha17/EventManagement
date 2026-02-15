package com.cactro.eventmanagement.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cactro.eventmanagement.entity.Users;

import jakarta.annotation.Nullable;


public class UserDetailsImpl implements UserDetails{
	
	Users user;
	public UserDetailsImpl (Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(
	            new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
	}

	@Override
	public @Nullable String getPassword() {
		return (this.user != null) ? this.user.getPassword() : "";
//		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.user.getUsername();
	}
	
	public Integer getUserId() {
		return this.user.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
