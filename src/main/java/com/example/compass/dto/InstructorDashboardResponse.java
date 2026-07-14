package com.example.compass.dto;


public class InstructorDashboardResponse {

    private long totalCourses;

    private long publishedCourses;

    private long draftCourses;

    private long archivedCourses;

    private long totalStudents;

    private double totalRevenue;

	public InstructorDashboardResponse(long totalCourses, long publishedCourses, long draftCourses,
			long archivedCourses, long totalStudents, double totalRevenue) {
		super();
		this.totalCourses = totalCourses;
		this.publishedCourses = publishedCourses;
		this.draftCourses = draftCourses;
		this.archivedCourses = archivedCourses;
		this.totalStudents = totalStudents;
		this.totalRevenue = totalRevenue;
	}

	public long getTotalCourses() {
		return totalCourses;
	}

	public void setTotalCourses(long totalCourses) {
		this.totalCourses = totalCourses;
	}

	public long getPublishedCourses() {
		return publishedCourses;
	}

	public void setPublishedCourses(long publishedCourses) {
		this.publishedCourses = publishedCourses;
	}

	public long getDraftCourses() {
		return draftCourses;
	}

	public void setDraftCourses(long draftCourses) {
		this.draftCourses = draftCourses;
	}

	public long getArchivedCourses() {
		return archivedCourses;
	}

	public void setArchivedCourses(long archivedCourses) {
		this.archivedCourses = archivedCourses;
	}

	public long getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(long totalStudents) {
		this.totalStudents = totalStudents;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public InstructorDashboardResponse() {
		super();
	}
    
}