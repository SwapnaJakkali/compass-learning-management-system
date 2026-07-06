package com.example.compass.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.compass.model.User;
import com.example.compass.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	public CustomUserDetailsService (UserRepository userRepository) {
		this.userRepository=userRepository;	
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> dbUser=userRepository.findByEmail(email);
		if(dbUser.isEmpty()) {
			throw new UsernameNotFoundException("User does not found");
		}else {
			return new CustomUserDetails(dbUser.get());
		}
	}

}
