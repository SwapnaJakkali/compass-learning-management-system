package com.example.compass.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.compass.enums.Category;
import com.example.compass.enums.CourseStatus;
import com.example.compass.enums.Level;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(length=2000)
	private String description;
	
	@Column(nullable=false)
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private Category category;

	@Enumerated(EnumType.STRING)
	private Level level;
	
	@Enumerated(EnumType.STRING)
	private CourseStatus courseStatus;
	
	private String thumbnail;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="instructor_id",nullable=false)
	private User instructor;

	
	public Course() {
		super();
	}


	public Course(Long id, String title, String description, BigDecimal price, Category category, Level level,
			CourseStatus courseStatus, String thumbnail, LocalDateTime createdAt, LocalDateTime updatedAt,
			User instructor) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.level = level;
		this.courseStatus = courseStatus;
		this.thumbnail = thumbnail;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.instructor = instructor;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Level getLevel() {
		return level;
	}


	public void setLevel(Level level) {
		this.level = level;
	}


	public CourseStatus getCourseStatus() {
		return courseStatus;
	}


	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public User getInstructor() {
		return instructor;
	}


	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}
	
	
}
