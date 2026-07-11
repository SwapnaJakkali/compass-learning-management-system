package com.example.compass.service;

import com.example.compass.model.Course;

public interface CourseAuthorizationService {
	Course getAuthorizedCourse(Long courseId);
}
