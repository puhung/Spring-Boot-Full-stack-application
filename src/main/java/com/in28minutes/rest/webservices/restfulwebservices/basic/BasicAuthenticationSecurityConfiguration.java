package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// call the basic auth url at the time of login to check if the token is valid
		http.authorizeHttpRequests(
				auth -> auth
						.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.anyRequest().authenticated() 
				);
		http.httpBasic(Customizer.withDefaults()); // Basic authentication for all requests
		
		http.sessionManagement(
				session -> session.sessionCreationPolicy
				(SessionCreationPolicy.STATELESS)); // STATELESS session
		
		http.csrf().disable(); //disable csrf
		return http.build(); 
	}
}