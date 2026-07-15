package com.example.compass.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.InstructorDashboardResponse;
import com.example.compass.service.InstructorDashboardService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;




@RestController
@RequestMapping("/api/instructor/dashboard")
@SecurityRequirement(name = "bearerAuth")
public class InstructorDashboardController {

	private final InstructorDashboardService instructorDashboardService;
	
	public InstructorDashboardController(InstructorDashboardService instructorDashboardService) {
		this.instructorDashboardService=instructorDashboardService;
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@GetMapping("/")
	public ResponseEntity<InstructorDashboardResponse> getDashboard() {
		InstructorDashboardResponse response=instructorDashboardService.getDashboard();
		return ResponseEntity.ok(response);
	}
	
}
