package com.example.compass.serviceImps;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.compass.model.User;
import com.example.compass.repository.UserRepository;
import com.example.compass.service.CurrentUserService;

@Service
public class CurrentCourseServiceImpl implements CurrentUserService{

	private final UserRepository userRepository;
	
	public CurrentCourseServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public User getCurrentUser() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		String email=authentication.getName();
		
		return userRepository.findByEmail(email)
				.orElseThrow(()->
					new RuntimeException("User not found"));
	}

}
