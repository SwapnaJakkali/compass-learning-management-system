package com.example.compass.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.SectionRequest;
import com.example.compass.dto.SectionResponse;
import com.example.compass.service.SectionService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/courses")
public class SectionController {

	private final SectionService sectionService;
	
	public SectionController(SectionService sectionService) {
		this.sectionService=sectionService;
	}
	
	@GetMapping("/{courseId}/sections")
	public ResponseEntity<List<SectionResponse>> getSections(@PathVariable Long courseId) {
		List<SectionResponse> sections = sectionService.getSectionsByCourse(courseId);
		return ResponseEntity.ok(sections) ;
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PostMapping("/{courseId}/sections")
	public ResponseEntity<SectionResponse> createSection(
			@PathVariable Long courseId,
			@Valid @RequestBody SectionRequest request) {
		SectionResponse response=sectionService.createSection(courseId, request);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	
}
