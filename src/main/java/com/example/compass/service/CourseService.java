package com.example.compass.service;

import java.util.List;

import com.example.compass.dto.CourseRequest;
import com.example.compass.dto.CourseResponse;
import com.example.compass.dto.StudentResponse;

import jakarta.validation.Valid;

public interface CourseService {
	
	CourseResponse createCourse(CourseRequest  request);

	List<CourseResponse> getAllCourses();

	void deleteCourse(Long courseId);

	CourseResponse updateCourse(Long courseId, @Valid CourseRequest request);

	CourseResponse publishCourse(Long courseId);

	CourseResponse draftCourse(Long courseId);

	CourseResponse archiveCourse(Long courseId);

	
}
