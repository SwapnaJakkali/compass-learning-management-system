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

import com.example.compass.dto.LectureRequest;
import com.example.compass.dto.LectureResponse;
import com.example.compass.service.LectureService;

import jakarta.validation.Valid;



@RequestMapping("api/sections")
@RestController	
public class LectureController {
	
	private final LectureService lectureService;
	
	public LectureController(LectureService lectureService) {
		this.lectureService=lectureService;
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PostMapping("/{sectionId}/lectures")
	public ResponseEntity<LectureResponse> createLecture(
			@PathVariable Long sectionId,
			@Valid @RequestBody LectureRequest request) {
		
		LectureResponse response = lectureService.createLecture(sectionId,request);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('INSTRUCTOR','STUDENT')")
	@GetMapping("/{sectionId}/lectures")
	public ResponseEntity<List<LectureResponse>> getLecturesBySection(@PathVariable Long sectionId) {
		
		List<LectureResponse> response = lectureService.getLectureBysection(sectionId);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
}
