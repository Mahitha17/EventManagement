package com.cactro.eventmanagement.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cactro.eventmanagement.service.MyUserDetailsService;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JwtFilter jwtFilter;
	
//	@Bean
//	 AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
//		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//		return provider;
//		
//	}
	@Bean
	AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setUserDetailsService(userDetailsService);
	    provider.setPasswordEncoder(passwordEncoder());
	    return provider;
	}
	@Bean
	 BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(12);
	}
	@Bean
	  SecurityFilterChain mySecurityConfig(HttpSecurity httpObj) throws Exception {
		httpObj.csrf(c->c.disable())
		.authorizeHttpRequests(r1->r1.requestMatchers("/register","/login","/v3/api-docs/**",
	            "/swagger-ui/**",
	            "/swagger-ui.html").permitAll().anyRequest().authenticated())
		.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return httpObj.build();
		
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	 @Bean
	     OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .components(new Components()
	                        .addSecuritySchemes("bearerAuth",
	                                new SecurityScheme()
	                                        .type(SecurityScheme.Type.HTTP)
	                                        .scheme("bearer")
	                                        .bearerFormat("JWT")))
	                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	    }

}
