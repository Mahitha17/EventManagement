package com.cactro.eventmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Users {
	@Id
	@SequenceGenerator(
	        name = "users_seq",
	        sequenceName = "users_seq", 
	        allocationSize = 1 
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "users_seq"
	)
	private Integer id;
	@Column(unique = true)
	private String email;
	private String username;
	private String password;
	private String role;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
