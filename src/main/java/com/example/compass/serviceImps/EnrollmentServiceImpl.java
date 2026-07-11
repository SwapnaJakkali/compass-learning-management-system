package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.compass.dto.CourseResponse;
import com.example.compass.dto.EnrollmentResponse;
import com.example.compass.dto.StudentResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.enums.Role;
import com.example.compass.exception.CourseNotFoundException;
import com.example.compass.exception.InvalidCourseStateException;
import com.example.compass.exception.UnauthorizedCourseAccessException;
import com.example.compass.mapper.CourseMapper;
import com.example.compass.mapper.UserMapper;
import com.example.compass.model.Course;
import com.example.compass.model.Enrollment;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.repository.EnrollmentRepository;
import com.example.compass.service.CourseAuthorizationService;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;
	private final CurrentUserService currentUserService;
	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;
	private final CourseAuthorizationService courseAuthorizationService;
	private final UserMapper userMapper;

	
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
			CurrentUserService currentUserService,
			CourseRepository courseRepository,
			CourseMapper courseMapper,
			CourseAuthorizationService courseAuthorizationService,
			UserMapper userMapper) {
		this.enrollmentRepository=enrollmentRepository;
		this.currentUserService=currentUserService;
		this.courseRepository=courseRepository;
		this.courseMapper=courseMapper;
		this.userMapper=userMapper;
		this.courseAuthorizationService=courseAuthorizationService;
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

	@Override
	public List<CourseResponse> getAllEnrolledCourses() {
		
		User currentUser=currentUserService.getCurrentUser();
		List<Enrollment> li =enrollmentRepository.findByStudentId(currentUser.getId());
		
		List<CourseResponse> response = new ArrayList<>();
		for(Enrollment e:li) {
			response.add(courseMapper.toResponse(e.getCourse()));
		}
		return response;
	}

	@Override
	public List<StudentResponse> getStudentsByCourse(Long courseId) {
		Course course=courseAuthorizationService.getAuthorizedCourse(courseId);
		
		List<Enrollment> li = enrollmentRepository.findByCourseId(courseId);
		List<StudentResponse> res=new ArrayList<>();
		for(Enrollment e:li) {
			res.add(userMapper.toStudentResponse(e.getStudent()));
		}
		return res;
	}
	
}
