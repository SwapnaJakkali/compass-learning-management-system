package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.compass.dto.CourseRequest;
import com.example.compass.dto.CourseResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.model.Course;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.service.CourseService;
import com.example.compass.service.CurrentUserService;

@Service
public class CourseServiceImpl implements CourseService{
	private final CourseRepository courseRepository;
	private final CurrentUserService currentUserService;
	
	public CourseServiceImpl(CourseRepository courseRepository , CurrentUserService currentUserService ) {
		this.courseRepository=courseRepository;
		this.currentUserService=currentUserService;
	}
	
	private CourseResponse mapToResponse(Course course) {
		CourseResponse response = new CourseResponse();

	    response.setId(course.getId());
	    response.setTitle(course.getTitle());
	    response.setDescription(course.getDescription());
	    response.setPrice(course.getPrice());
	    response.setCategory(course.getCategory());
	    response.setLevel(course.getLevel());
	    response.setCourseStatus(course.getCourseStatus());
	    response.setThumbnailUrl(course.getThumbnailUrl());

	    response.setInstructorName(
	            course.getInstructor().getFirstName()
	            + " "
	            + course.getInstructor().getLastName());

	    return response;
	}

	@Override
	public CourseResponse createCourse(CourseRequest request) {
		User instructor = currentUserService.getCurrentUser();
		Course course = new Course();
		
		course.setTitle(request.getTitle());
		course.setDescription(request.getDescription());
		course.setPrice(request.getPrice());
		course.setCategory(request.getCategory());
		course.setLevel(request.getLevel());
		course.setThumbnailUrl(request.getThumbnailUrl());
		
		
		course.setCourseStatus(CourseStatus.DRAFT);
		course.setCreatedAt(LocalDateTime.now());
		course.setUpdatedAt(LocalDateTime.now());
		course.setInstructor(instructor);
		
		Course savedCourse=courseRepository.save(course);
//		CourseResponse response = courseMapper.toResponse(savedCourse);
		
		
		return mapToResponse(savedCourse);
	}

	@Override
	public List<CourseResponse> getAllCourses() {
		// TODO Auto-generated method stub
		List<Course> courses = courseRepository.findAll();
		return courses.stream()
			.map(course->{
	            return mapToResponse(course);
			}).toList();
	}
	
	
}
