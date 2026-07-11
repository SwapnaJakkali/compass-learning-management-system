package com.example.compass.service;

import java.util.List;

import com.example.compass.dto.CourseResponse;
import com.example.compass.dto.EnrollmentResponse;
import com.example.compass.dto.StudentResponse;

public interface EnrollmentService {

	public EnrollmentResponse enroll(Long courseId);

	public List<CourseResponse> getAllEnrolledCourses();

	public List<StudentResponse> getStudentsByCourse(Long courseId);

}
