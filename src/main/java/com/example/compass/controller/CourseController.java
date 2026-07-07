package com.example.compass.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.CourseRequest;
import com.example.compass.dto.CourseResponse;
import com.example.compass.service.CourseService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/courses")
public class CourseController {
	private final CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService=courseService;
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")	
	@PostMapping("/")
	public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest request) {
		CourseResponse response=courseService.createCourse(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PreAuthorize("hasRole('STUDENT') or hasRole('INSTRUCTOR')")
//	@PreAuthorize("hasAnyRole('STUDENT', 'INSTRUCTOR', 'ADMIN')")
	@GetMapping("/")
	public  ResponseEntity<List<CourseResponse>> getMethodName() {
		List<CourseResponse> li =  courseService.getAllCourses();
		
		return ResponseEntity.status(HttpStatus.OK).body(li);
	}

	
	
	
}
