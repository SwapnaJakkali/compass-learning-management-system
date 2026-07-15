package com.example.compass.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.EnrollmentResponse;
import com.example.compass.dto.StudentResponse;
import com.example.compass.service.EnrollmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/enrollments")
@SecurityRequirement(name = "bearerAuth")
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
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@GetMapping("/{courseId}/students")
	public ResponseEntity<List<StudentResponse>> getStudentsByCourse(@PathVariable Long courseId) {
		List<StudentResponse> li=enrollmentService.getStudentsByCourse(courseId);
		return ResponseEntity.ok(li);
	}
	
}
