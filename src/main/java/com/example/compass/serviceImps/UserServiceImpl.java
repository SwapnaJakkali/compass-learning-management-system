package com.example.compass.serviceImps;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.compass.dto.RegisterRequest;
import com.example.compass.enums.Role;
import com.example.compass.model.User;
import com.example.compass.repository.UserRepository;
import com.example.compass.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;




	public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
	    this.userRepository = userRepository;
	    this.passwordEncoder=passwordEncoder;
	}


	User user = new User();

	@Override
	public void register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())) {
			System.out.println("user already exists please login");
			return ;
		}
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setRole(Role.STUDENT);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		userRepository.save(user);

	}



}
