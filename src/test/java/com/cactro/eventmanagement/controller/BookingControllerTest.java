package com.cactro.eventmanagement.controller;




import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import com.cactro.eventmanagement.dto.BookingRequest;
import com.cactro.eventmanagement.dto.BookingResponseDTO;
import com.cactro.eventmanagement.service.BookingsService;
import com.cactro.eventmanagement.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {


	    @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private JwtService jwtService;
	    
	    @Test
	    void login_shouldReturnToken_whenCredentialsAreValid() throws Exception {

	        String json = """
	            {
	                "username": "Username1",
	                "password": "Username1"
	            }
	        """;

	        mockMvc.perform(post("/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect(status().isOk())
	                .andExpect(content().string(notNullValue()));
	    }
	    
	    @Test
	    void login_shouldFail_whenPasswordIsWrong() throws Exception {

	        String json = """
	            {
	                "username": "Username1",
	                "password": "wrong"
	            }
	        """;

	        mockMvc.perform(post("/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect(status().isUnauthorized());
	    }
	    
	    @Test
	    void booking_shouldWork_forCustomer() throws Exception {

	        String token = jwtService.generateToken(
	            new org.springframework.security.core.userdetails.User(
	                    "Username1",
	                    "Username1",
	                    List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
	            )
	        );

	        mockMvc.perform(post("/bookings/book")
	                .header("Authorization", "Bearer " + token)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"eventId\":8,\"ticketCount\":10}"))
	                .andExpect(status().isOk());
	    }

}
