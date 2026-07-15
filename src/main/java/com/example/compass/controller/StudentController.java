package com.example.compass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.CourseResponse;
import com.example.compass.service.EnrollmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/students")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {
	
	private final EnrollmentService enrollmentService ;
	
	public StudentController(EnrollmentService enrollmentService) {
		this.enrollmentService=enrollmentService;
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@GetMapping("/my-courses")
	public ResponseEntity<List<CourseResponse>> allCourses() {
		
		List<CourseResponse> response=enrollmentService.getAllEnrolledCourses();
		return ResponseEntity.ok(response);
	}
	
}
