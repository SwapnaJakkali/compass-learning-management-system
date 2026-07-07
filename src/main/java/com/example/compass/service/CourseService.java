package com.example.compass.service;

import java.util.List;

import com.example.compass.dto.CourseRequest;
import com.example.compass.dto.CourseResponse;

public interface CourseService {
	
	CourseResponse createCourse(CourseRequest  request);

	List<CourseResponse> getAllCourses();
	
}
