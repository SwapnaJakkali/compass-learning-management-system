package com.example.compass.exception;

public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException(String message) {
		super(message);
	}
}
