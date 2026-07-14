package com.example.compass.dto;


import java.util.List;

public class StudentDashboardResponse {

    private long totalEnrolledCourses;
    private List<RecentEnrollmentResponse> recentEnrollments;

    public StudentDashboardResponse() {
    }

    public long getTotalEnrolledCourses() {
        return totalEnrolledCourses;
    }

    public void setTotalEnrolledCourses(long totalEnrolledCourses) {
        this.totalEnrolledCourses = totalEnrolledCourses;
    }

    public List<RecentEnrollmentResponse> getRecentEnrollments() {
        return recentEnrollments;
    }

    public void setRecentEnrollments(List<RecentEnrollmentResponse> recentEnrollments) {
        this.recentEnrollments = recentEnrollments;
    }

	
}
