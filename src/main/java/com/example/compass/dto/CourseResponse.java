package com.example.compass.dto;

import java.math.BigDecimal;

import com.example.compass.enums.Category;
import com.example.compass.enums.CourseStatus;
import com.example.compass.enums.Level;

public class CourseResponse {
	private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private Category category;

    private Level level;

    private CourseStatus status;

    private String thumbnailUrl;

    private String instructorName;

	public CourseResponse(Long id, String title, String description, BigDecimal price, Category category, Level level,
			CourseStatus status, String thumbnailUrl, String instructorName) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.level = level;
		this.status = status;
		this.thumbnailUrl = thumbnailUrl;
		this.instructorName = instructorName;
	}

	public CourseResponse() {
		super();
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

	public CourseStatus getStatus() {
		return status;
	}

	public void setStatus(CourseStatus status) {
		this.status = status;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
    
    
    
}
