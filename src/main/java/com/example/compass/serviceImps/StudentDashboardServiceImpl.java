package com.example.compass.serviceImps;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.compass.dto.RecentEnrollmentResponse;
import com.example.compass.dto.StudentDashboardResponse;
import com.example.compass.model.Enrollment;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.repository.EnrollmentRepository;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.StudentDashboardService;

@Service
public class StudentDashboardServiceImpl implements StudentDashboardService{

	private final CurrentUserService currentUserService;
	private final CourseRepository courseRepository;
	private final EnrollmentRepository enrollmentRepository;
	
	public StudentDashboardServiceImpl(CurrentUserService currentUserService,
			CourseRepository courseRepository,
			EnrollmentRepository enrollmentRepository) {
		this.currentUserService=currentUserService;
		this.courseRepository=courseRepository;
		this.enrollmentRepository=enrollmentRepository;
	}
	
	private List<RecentEnrollmentResponse> getRecentEnrollments(User student){
		Pageable pageable = PageRequest.of(0, 5,Sort.by("enrolledat").descending());
		Page<Enrollment>  enrollments  = enrollmentRepository.findByStudent(student,pageable);
		
		return enrollments.getContent()
				.stream()
				.map(this::mapToRecentEnrollmentResponse)
				.toList();
	}
	
	private RecentEnrollmentResponse mapToRecentEnrollmentResponse(Enrollment enrollment) {

	    RecentEnrollmentResponse response = new RecentEnrollmentResponse();

	    response.setCourseId(enrollment.getCourse().getId());
	    response.setTitle(enrollment.getCourse().getTitle());
	    response.setThumbnailUrl(enrollment.getCourse().getThumbnailUrl());

	    response.setInstructorName(
	            enrollment.getCourse()
	                      .getInstructor()
	                      .getFirstName());

	    response.setEnrolledAt(enrollment.getEnrolledat());

	    return response;
	}
	
	@Override
	public StudentDashboardResponse getDashboard() {
		User student = currentUserService.getCurrentUser();
		long totalCourses=enrollmentRepository.countByStudent(student);

		List<RecentEnrollmentResponse> recentEnrollments=getRecentEnrollments(student);
		
		StudentDashboardResponse response = new StudentDashboardResponse();
		response.setTotalEnrolledCourses(totalCourses);
		response.setRecentEnrollments(recentEnrollments);
		return response;
	}

}
