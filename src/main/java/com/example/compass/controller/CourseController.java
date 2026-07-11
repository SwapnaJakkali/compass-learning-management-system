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

import com.example.compass.dto.CourseRequest;
import com.example.compass.dto.CourseResponse;
import com.example.compass.dto.StudentResponse;
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
//		System.out.println("hi");
		return ResponseEntity.status(HttpStatus.OK).body(li);
	}

	
	@PutMapping("/{courseId}")
	public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long courseId, 
			@Valid @RequestBody CourseRequest request) {
		
		CourseResponse response = courseService.updateCourse(courseId , request);
		
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasAnyrole('INSTRUCTOR')")
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId){
		courseService.deleteCourse(courseId);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PatchMapping("/{courseId}/publish")
	public ResponseEntity<CourseResponse> publishCourse(@PathVariable Long courseId){
		CourseResponse response=courseService.publishCourse(courseId);
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PatchMapping("/{courseId}/draft")
	public ResponseEntity<CourseResponse> draftCourse(@PathVariable Long courseId){
		CourseResponse response=courseService.draftCourse(courseId);
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PatchMapping("/{courseId}/archive")
	public ResponseEntity<CourseResponse> archiveCourse(@PathVariable Long courseId){
		CourseResponse response=courseService.archiveCourse(courseId);
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
}
