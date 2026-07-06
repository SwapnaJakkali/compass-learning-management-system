package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.compass.dto.LoginRequest;
import com.example.compass.dto.LoginResponse;
import com.example.compass.dto.RegisterRequest;
import com.example.compass.enums.Role;
import com.example.compass.model.User;
import com.example.compass.repository.UserRepository;
import com.example.compass.security.JwtUtil;
import com.example.compass.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

//	private final AuthenticationManager authenticationManager;


	public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
	    this.userRepository = userRepository;
	    this.passwordEncoder=passwordEncoder;
	    this.jwtUtil=jwtUtil;
	}
//	public UserServiceImpl(
//	        UserRepository userRepository,
//	        PasswordEncoder passwordEncoder,
//	        JwtUtil jwtUtil,
//	        AuthenticationManager authenticationManager) {
//		this.userRepository = userRepository;
//	    this.passwordEncoder=passwordEncoder;
//	    this.jwtUtil=jwtUtil;
//	    this.authenticationManager = authenticationManager;
//	}


	@Override
	public void register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
		}
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setRole(Role.STUDENT);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		userRepository.save(user);

	}




	@Override
	public LoginResponse login(LoginRequest request) throws RuntimeException {
		Optional<User> opUser=userRepository.findByEmail(request.getEmail());
		if(opUser.isEmpty()) {
			throw new RuntimeException("user does not exist , please register");
		}else {
			User dbUser=opUser.get();
			if(!passwordEncoder.matches(request.getPassword(),dbUser.getPassword())) {
				throw new RuntimeException("Invalid email or password");
			}
			String token=jwtUtil.generateToken(dbUser.getEmail());
			return new LoginResponse(token);
		}
	}



}
