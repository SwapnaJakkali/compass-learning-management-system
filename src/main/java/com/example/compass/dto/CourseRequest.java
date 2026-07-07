package com.example.compass.dto;

import java.math.BigDecimal;

import com.example.compass.enums.Category;
import com.example.compass.enums.Level;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseRequest {
	
	@NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Level is required")
    private Level level;

    private String thumbnailUrl;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public CourseRequest(String title,
            String description,
            BigDecimal price,
            Category category,
            Level level,
            String thumbnailUrl) {

		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.level = level;
		this.thumbnailUrl = thumbnailUrl;
}

	public CourseRequest() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
}
