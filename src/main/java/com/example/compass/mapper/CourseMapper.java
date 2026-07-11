package com.example.compass.mapper;

import org.springframework.stereotype.Component;

import com.example.compass.dto.CourseResponse;
import com.example.compass.model.Course;

@Component
public class CourseMapper {
	
	public CourseResponse toResponse(Course course) {
		CourseResponse response = new CourseResponse();
		
		response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setPrice(course.getPrice());
        response.setCategory(course.getCategory());
        response.setLevel(course.getLevel());
        response.setCourseStatus(course.getCourseStatus());
        response.setThumbnailUrl(course.getThumbnailUrl());
		response.setInstructorName(course.getInstructor().getFirstName()+" "+course.getInstructor().getLastName());
		
		return response;
	}
}
