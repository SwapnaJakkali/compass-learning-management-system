package com.example.compass.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.compass.dto.LectureRequest;
import com.example.compass.dto.LectureResponse;
import com.example.compass.service.LectureService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;




@RequestMapping("api/sections")
@RestController	
@SecurityRequirement(name = "bearerAuth")
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
	
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping("lectures/{lectureId}")
	public ResponseEntity<LectureResponse> updateLecture(
			@PathVariable Long lectureId, 
			@Valid @RequestBody LectureRequest request) {

		LectureResponse response = lectureService.updateLecture(lectureId,request);
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping("/lectures/{lectureId}")
	public ResponseEntity<Void> deleteLecture(@PathVariable Long lectureId){
		lectureService.deleteLecture(lectureId);
		return ResponseEntity.noContent().build();
	}
	
	
	@PatchMapping("/{lectureId}/video")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public ResponseEntity<LectureResponse> updateVideo(
	        @PathVariable Long lectureId,
	        @RequestParam("file") MultipartFile file) {

	    LectureResponse response =
	            lectureService.updateVideo(lectureId, file);

	    return ResponseEntity.ok(response);
	}
	
}
