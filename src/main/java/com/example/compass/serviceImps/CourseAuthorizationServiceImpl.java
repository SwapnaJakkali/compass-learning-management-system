package com.example.compass.serviceImps;

import org.springframework.stereotype.Service;

import com.example.compass.exception.CourseNotFoundException;
import com.example.compass.exception.UnauthorizedCourseAccessException;
import com.example.compass.model.Course;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.service.CourseAuthorizationService;
import com.example.compass.service.CurrentUserService;

@Service
public class CourseAuthorizationServiceImpl implements CourseAuthorizationService{

	private final CourseRepository courseRepository;
	private final CurrentUserService currentUserService;
	
	public CourseAuthorizationServiceImpl(CourseRepository courseRepository,CurrentUserService currentUserService) {
		this.courseRepository=courseRepository;
		this.currentUserService=currentUserService;
	}
	
	@Override
	public Course getAuthorizedCourse(Long courseId) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(()-> new CourseNotFoundException("Course not found with id : "+courseId));
		
		User currentUser=currentUserService.getCurrentUser();
		if(!course.getInstructor().getId().equals(currentUser.getId())) {
			throw new UnauthorizedCourseAccessException("You are not allowed to acess this course");
		}
		return course;
	}

	
}
