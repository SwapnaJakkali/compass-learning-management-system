package com.example.compass.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		key=Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String email) {
		return Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(key)
				.compact();
		
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return extractAllClaims(token).getSubject();
	}
	
	private Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public boolean validateToken(String token,UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

}
