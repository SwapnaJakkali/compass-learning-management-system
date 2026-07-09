package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.compass.dto.CourseRequest;
import com.example.compass.dto.CourseResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.exception.CourseNotFoundException;
import com.example.compass.exception.InvalidCourseStateException;
import com.example.compass.exception.UnauthorizedCourseAccessException;
import com.example.compass.model.Course;
import com.example.compass.model.Section;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.service.CourseService;
import com.example.compass.service.CurrentUserService;

import jakarta.validation.Valid;

@Service
public class CourseServiceImpl implements CourseService{
	private final CourseRepository courseRepository;
	private final CurrentUserService currentUserService;
	
	public CourseServiceImpl(CourseRepository courseRepository , CurrentUserService currentUserService ) {
		this.courseRepository=courseRepository;
		this.currentUserService=currentUserService;
	}
	
	private Course getAuthorizedCourse(Long courseId) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(()->new CourseNotFoundException("Course Not Found"));
		
		User currentUser = currentUserService.getCurrentUser();
		if(!currentUser.getId().equals(course.getInstructor().getId())) {
			throw new UnauthorizedCourseAccessException("You are not Authoried to modify this course");
		}
		return course;
	}
	
	private void validateCourseForPublish(Course course) {
		if(course.getCourseStatus()==CourseStatus.ARCHIVED) {
			throw new InvalidCourseStateException("Archived course cannot be published");
		}
		if(course.getSections().isEmpty()) {
			throw new InvalidCourseStateException("Course must contain atleast one Section");
		}
		for(Section sections:course.getSections()) {
			if(sections.getLectures().isEmpty()) {
				throw new InvalidCourseStateException("Every Section must have at leat one lecture");
			}
		}
		 
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
		
		return mapToResponse(savedCourse);
	}

	@Override
	public List<CourseResponse> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		return courses.stream()
			.map(course->{
	            return mapToResponse(course);
			}).toList();
	}

	@Override
	public void deleteCourse(Long courseId) {

		Course course=getAuthorizedCourse(courseId);
		
		courseRepository.delete(course);
		
	}

	@Override
	public CourseResponse updateCourse(Long courseId, @Valid CourseRequest request) {
		Course course = getAuthorizedCourse(courseId);
		
		course.setCategory(request.getCategory());
		course.setDescription(request.getDescription());
		course.setTitle(request.getTitle());
		course.setPrice(request.getPrice());
		course.setThumbnailUrl(request.getThumbnailUrl());
		course.setUpdatedAt(LocalDateTime.now());
		
		return mapToResponse(course);
	}

	@Override
	public CourseResponse publishCourse(Long courseId) {
		
		Course course =getAuthorizedCourse(courseId);
		
		validateCourseForPublish(course);
		
		course.setCourseStatus(CourseStatus.PUBLISHED);
		course.setUpdatedAt(LocalDateTime.now());
		
		Course updatedCourse=courseRepository.save(course);
		
		return mapToResponse(updatedCourse);
	}

	
	
	@Override
	public CourseResponse draftCourse(Long courseId) {
		
		Course course =getAuthorizedCourse(courseId);
		
		course.setCourseStatus(CourseStatus.DRAFT);
		course.setUpdatedAt(LocalDateTime.now());
		
		Course updatedCourse=courseRepository.save(course);
		
		return mapToResponse(updatedCourse);
	}

	@Override
	public CourseResponse archiveCourse(Long courseId) {
		
		Course course =getAuthorizedCourse(courseId);
		
		course.setCourseStatus(CourseStatus.ARCHIVED);
		course.setUpdatedAt(LocalDateTime.now());
		
		Course updatedCourse=courseRepository.save(course);
		
		return mapToResponse(updatedCourse);
	}
	
	
}
