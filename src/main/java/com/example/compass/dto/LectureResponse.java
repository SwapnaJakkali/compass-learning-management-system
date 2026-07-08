package com.example.compass.dto;

public class LectureResponse {
	private Long id;

	private String title;

	private String description;

	private String videoUrl;

	private Integer lectureOrder;

	private Integer durationInSeconds;

	private boolean previewFree;

	public LectureResponse(Long id, String title, String description, String videoUrl, Integer lectureOrder,
			Integer durationInSeconds, boolean previewFree) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.videoUrl = videoUrl;
		this.lectureOrder = lectureOrder;
		this.durationInSeconds = durationInSeconds;
		this.previewFree = previewFree;
	}

	public LectureResponse() {
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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getLectureOrder() {
		return lectureOrder;
	}

	public void setLectureOrder(Integer lectureOrder) {
		this.lectureOrder = lectureOrder;
	}

	public Integer getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(Integer durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}

	public boolean isPreviewFree() {
		return previewFree;
	}

	public void setPreviewFree(boolean previewFree) {
		this.previewFree = previewFree;
	}

	
	
}
