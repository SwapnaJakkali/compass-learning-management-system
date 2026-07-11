package com.example.compass.mapper;

import org.springframework.stereotype.Component;

import com.example.compass.dto.StudentResponse;
import com.example.compass.model.User;

@Component
public class UserMapper {
	
	public StudentResponse toStudentResponse(User user) {
		StudentResponse response = new StudentResponse();
		
		response.setEmail(user.getEmail());
		response.setId(user.getId());
		response.setName(user.getFirstName());
		
		return response;
	}
}
