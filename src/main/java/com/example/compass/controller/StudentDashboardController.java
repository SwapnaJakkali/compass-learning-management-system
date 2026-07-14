package com.example.compass.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.StudentDashboardResponse;
import com.example.compass.service.StudentDashboardService;


@RestController
@RequestMapping("api/student/dashboard")
public class StudentDashboardController {

	private final StudentDashboardService studentDashboardService;
	
	public StudentDashboardController(StudentDashboardService studentDashboardService) {
		this.studentDashboardService=studentDashboardService;
	}
	
	@GetMapping("/")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<StudentDashboardResponse> getMethodName() {
		StudentDashboardResponse response=studentDashboardService.getDashboard();
		return ResponseEntity.ok(response);
	}
	
}
