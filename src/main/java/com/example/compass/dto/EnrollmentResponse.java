package com.example.compass.dto;

import java.time.LocalDateTime;

public class EnrollmentResponse {

	private Long enrollmentId;
    private Long courseId;
    private String courseTitle;
    private LocalDateTime enrolledAt;
    private String message;
    
    public EnrollmentResponse() {
    }

    public EnrollmentResponse(Long enrollmentId,
                              Long courseId,
                              String courseTitle,
                              LocalDateTime enrolledAt,
                              String message) {
        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.enrolledAt = enrolledAt;
        this.message = message;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
