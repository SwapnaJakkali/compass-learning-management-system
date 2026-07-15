package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.compass.dto.CourseRequest;
import com.example.compass.dto.CourseResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.exception.InvalidCourseStateException;
import com.example.compass.mapper.CourseMapper;
import com.example.compass.model.Course;
import com.example.compass.model.Section;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.service.CourseAuthorizationService;
import com.example.compass.service.CourseService;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.FileUploadService;

import jakarta.validation.Valid;

@Service
public class CourseServiceImpl implements CourseService{
	
	private final CourseRepository courseRepository;
	private final CurrentUserService currentUserService;
	private final CourseMapper courseMapper;
	private final CourseAuthorizationService courseAuthorizationService;
	private final FileUploadService fileUploadService;
	
	public CourseServiceImpl(CourseRepository courseRepository , 
			CurrentUserService currentUserService ,
			CourseMapper courseMapper,
			CourseAuthorizationService courseAuthorizationService,
			FileUploadService fileUploadService) {
		this.courseRepository=courseRepository;
		this.currentUserService=currentUserService;
		this.courseMapper=courseMapper;
		this.courseAuthorizationService=courseAuthorizationService;
		this.fileUploadService=fileUploadService;
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
		
		return courseMapper.toResponse(savedCourse);
	}

	@Override
	public List<CourseResponse> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		return courses.stream()
			.map(course->{
	            return courseMapper.toResponse(course);
			}).toList();
	}

	@Override
	public void deleteCourse(Long courseId) {

		Course course=courseAuthorizationService.getAuthorizedCourse(courseId);;
		
		courseRepository.delete(course);
		
	}

	@Override
	public CourseResponse updateCourse(Long courseId, @Valid CourseRequest request) {
		Course course = courseAuthorizationService.getAuthorizedCourse(courseId);;
		
		course.setCategory(request.getCategory());
		course.setDescription(request.getDescription());
		course.setTitle(request.getTitle());
		course.setPrice(request.getPrice());
		course.setThumbnailUrl(request.getThumbnailUrl());
		course.setUpdatedAt(LocalDateTime.now());
		
		return courseMapper.toResponse(course);
	}

	@Override
	public CourseResponse publishCourse(Long courseId) {
		
		Course course =courseAuthorizationService.getAuthorizedCourse(courseId);;
		
		validateCourseForPublish(course);
		
		course.setCourseStatus(CourseStatus.PUBLISHED);
		course.setUpdatedAt(LocalDateTime.now());
		
		Course updatedCourse=courseRepository.save(course);
		
		return courseMapper.toResponse(updatedCourse);
	}

	
	
	@Override
	public CourseResponse draftCourse(Long courseId) {
		
		Course course =courseAuthorizationService.getAuthorizedCourse(courseId);
		
		course.setCourseStatus(CourseStatus.DRAFT);
		course.setUpdatedAt(LocalDateTime.now());
		
		Course updatedCourse=courseRepository.save(course);
		
		return courseMapper.toResponse(updatedCourse);
	}

	@Override
	public CourseResponse archiveCourse(Long courseId) {
		
		Course course =courseAuthorizationService.getAuthorizedCourse(courseId);
		
		course.setCourseStatus(CourseStatus.ARCHIVED);
		course.setUpdatedAt(LocalDateTime.now());
		
		Course updatedCourse=courseRepository.save(course);
		
		return courseMapper.toResponse(updatedCourse);
	}

	@Override
	public CourseResponse updateThumbnail(Long courseId, MultipartFile file) {

		Course course=courseAuthorizationService.getAuthorizedCourse(courseId);
		String thumbnailUrl=fileUploadService.uploadImage(file,"course-thumbnails");
		
		course.setThumbnailUrl(thumbnailUrl);
		Course updatedCourse=courseRepository.save(course);
		
		return courseMapper.toResponse(updatedCourse);
	}

	
	
	
}
