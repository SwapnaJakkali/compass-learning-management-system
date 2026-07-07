package com.example.compass.dto;

public class SectionResponse {
	private Long id;
	
	private String title;
	
	private String description;
	
	private Integer sectionOrder;

	public SectionResponse(Long id, String title, String description, Integer sectionOrder) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.sectionOrder = sectionOrder;
	}

	public SectionResponse() {
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

	public Integer getSectionOrder() {
		return sectionOrder;
	}

	public void setSectionOrder(Integer sectionOrder) {
		this.sectionOrder = sectionOrder;
	}
	
	
}
