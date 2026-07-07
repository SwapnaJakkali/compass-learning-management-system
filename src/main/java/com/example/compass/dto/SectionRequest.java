package com.example.compass.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SectionRequest {
	@NotBlank(message="Title is required")
	private String title;
	
	private String description;
	
	@NotNull(message="Section order is required")
	@Min(value=1,message="Section order must be at least 1")
	private Integer sectionOrder;

	public SectionRequest(@NotBlank(message = "Title is required") String title, String description,
			@NotNull(message = "Section order is required") @Min(value = 1, message = "Section order must be at least 1") Integer sectionOrder) {
		super();
		this.title = title;
		this.description = description;
		this.sectionOrder = sectionOrder;
	}

	public SectionRequest() {
		super();
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

	public Integer getSectionOrder() {
		return sectionOrder;
	}

	public void setSectionOrder(Integer sectionOrder) {
		this.sectionOrder = sectionOrder;
	}
	
	
}
