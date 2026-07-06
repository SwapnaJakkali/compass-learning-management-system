package com.example.compass.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtUtil jwtUtil,CustomUserDetailsService userDetailsService) {
		this.jwtUtil=jwtUtil;
		this.userDetailsService=userDetailsService;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authHeader=request.getHeader("Authorization");
		
//		System.out.println(request.getRequestURI());
//		System.out.println(authHeader);
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
//			System.out.println("hi");
			return;
		}
		String jwt = authHeader.substring(7);
		String email=jwtUtil.extractUsername(jwt);
//		System.out.println("hi1");
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		if(jwtUtil.validateToken(jwt, userDetails)) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//			System.out.println("hi2");
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);

		 	
	}
	

}
