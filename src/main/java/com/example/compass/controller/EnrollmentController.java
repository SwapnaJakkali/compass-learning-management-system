package com.example.compass.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.EnrollmentResponse;
import com.example.compass.service.EnrollmentService;


@RestController
@RequestMapping("/api")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;
	
	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService=enrollmentService;
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@PostMapping("/courses/{courseId}/enroll")
	public ResponseEntity<EnrollmentResponse> enrollCourse(@PathVariable Long courseId) {
		EnrollmentResponse response = enrollmentService.enroll(courseId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
