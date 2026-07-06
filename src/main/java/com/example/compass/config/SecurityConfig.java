package com.example.compass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.compass.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter=jwtAuthenticationFilter;
	}
	


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(c->c.disable())
				.authorizeHttpRequests(a->a
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/instructor/**").hasRole("INSTRUCTOR")
						.requestMatchers("/api/student/**").hasRole("STUDENT")
						.requestMatchers("/api/auth/**").permitAll()
						.anyRequest().authenticated()
						)
				.sessionManagement(session -> session
		                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // ← ADD THIS
		            )
		        .exceptionHandling(exception -> exception  // ← ADD THIS!
		        .authenticationEntryPoint((request, response, authException) -> {
		                    // This handles 401 - when not authenticated
		                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
		                    response.getWriter().write("Authentication required");
		         })
		         .accessDeniedHandler((request, response, accessDeniedException) -> {
		                    // This handles 403 - when authenticated but not authorized
		                    response.setStatus(HttpStatus.FORBIDDEN.value());
		                    response.getWriter().write("Access Denied: " + accessDeniedException.getMessage());
		          })
		          )
				.httpBasic(Customizer.withDefaults())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
