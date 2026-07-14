package com.example.compass.serviceImps;

import org.springframework.stereotype.Service;

import com.example.compass.dto.InstructorDashboardResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.repository.EnrollmentRepository;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.InstructorDashboardService;

@Service
public class InstructorDashboardServiceImpl implements InstructorDashboardService{

	private final CurrentUserService currentUserService;
	private final CourseRepository courseRepository;
	private final EnrollmentRepository enrollmentRepository;
	public InstructorDashboardServiceImpl(CurrentUserService currentUserService,
			CourseRepository courseRepository,
			EnrollmentRepository enrollmentRepository) {
		this.currentUserService=currentUserService;
		this.courseRepository=courseRepository;
		this.enrollmentRepository=enrollmentRepository;
	}
	
	
	@Override
	public InstructorDashboardResponse getDashboard() {
		User instructor=currentUserService.getCurrentUser();
		long totalCourses=courseRepository.countByInstructor(instructor);
		long publishedCourses = courseRepository.countByInstructorAndCourseStatus(instructor,CourseStatus.PUBLISHED);
		long draftCourses = courseRepository.countByInstructorAndCourseStatus(instructor,CourseStatus.DRAFT);
		long archivedCourses = courseRepository.countByInstructorAndCourseStatus(instructor,CourseStatus.ARCHIVED);
		long totalStudents = enrollmentRepository.countByCourseInstructor(instructor);
		
		InstructorDashboardResponse response = new InstructorDashboardResponse();
		response.setTotalCourses(totalCourses);
		response.setPublishedCourses(publishedCourses);
		response.setArchivedCourses(archivedCourses);
		response.setDraftCourses(draftCourses);
		response.setTotalStudents(totalStudents);
		return response;
	}

}
