package com.example.compass.serviceImps;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.compass.dto.EnrollmentResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.enums.Role;
import com.example.compass.exception.CourseNotFoundException;
import com.example.compass.exception.InvalidCourseStateException;
import com.example.compass.exception.UnauthorizedCourseAccessException;
import com.example.compass.model.Course;
import com.example.compass.model.Enrollment;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.repository.EnrollmentRepository;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;
	private final CurrentUserService currentUserService;
	private final CourseRepository courseRepository;
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
			CurrentUserService currentUserService,
			CourseRepository courseRepository) {
		this.enrollmentRepository=enrollmentRepository;
		this.currentUserService=currentUserService;
		this.courseRepository=courseRepository;
	}
	
	@Override
	public EnrollmentResponse enroll(Long courseId) {
		User student=currentUserService.getCurrentUser();
		
		Course course = courseRepository.findById(courseId)
				.orElseThrow(()->new CourseNotFoundException("Course is not found"));
		if(course.getCourseStatus()!=CourseStatus.PUBLISHED){
				throw new InvalidCourseStateException("Course is not available for enrollment");
		}
		
		if(student.getRole()!=Role.STUDENT) {
			throw new UnauthorizedCourseAccessException("You are not allowed to enroll course");
		}
		
		boolean alreadyEnrolled=enrollmentRepository.existsByStudentIdAndCourseId(student.getId(),courseId);

		if(alreadyEnrolled) {
			throw new InvalidCourseStateException("You are already enrolled in this course");
		}
		
		Enrollment enrollment = new Enrollment();
		enrollment.setEnrolledat(LocalDateTime.now());
		enrollment.setCourse(course);
		enrollment.setStudent(student);
		
		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
		
		return new  EnrollmentResponse(
				savedEnrollment.getId(),
				course.getId(),
				course.getTitle(),
				savedEnrollment.getEnrolledat(),
				"Successfully enrolled in the course");
	}

}
