package com.example.compass.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.CourseResponse;
import com.example.compass.service.CatalogService;


@RequestMapping("/api/catalog")
@RestController
public class CatalogController {
	
	private final CatalogService catalogService;
	
	public CatalogController(CatalogService catalogService) {
		this.catalogService=catalogService;
	}
	
	@GetMapping("/courses")
	public ResponseEntity<Page<CourseResponse>> getPublishedCourses(
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="10") int size,
			@RequestParam(defaultValue = "title") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		
		Page<CourseResponse> li=catalogService.getPublishedCourses(page , size , sortBy , direction); 
		return ResponseEntity.ok(li);
	}
	
}
