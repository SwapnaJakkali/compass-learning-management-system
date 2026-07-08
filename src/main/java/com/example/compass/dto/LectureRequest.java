package com.example.compass.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LectureRequest {
	
	@NotBlank(message = "Title is required")
	private String title;
	
	@Size(max = 1000, message = "Description cannot exceed 1000 characters")
	private String description;
	
	@NotBlank(message = "Video URL is required")
	private String videoUrl;
	
	@NotNull(message = "Lecture order is required")
	@Min(value = 1, message = "Lecture order must be at least 1")
	private Integer lectueOder;
	
	@NotNull(message = "Duration is required")
	@Min(value = 1, message = "Duration must be greater than 0 seconds")
	private Integer durationSeconds;
	
	private boolean previewFree;

	public LectureRequest(@NotBlank(message = "Title is required") String title,
			@Size(max = 1000, message = "Description cannot exceed 1000 characters") String description,
			@NotBlank(message = "Video URL is required") String videoUrl,
			@NotNull(message = "Lecture order is required") @Min(value = 1, message = "Lecture order must be at least 1") Integer lectueOder,
			@NotNull(message = "Duration is required") @Min(value = 1, message = "Duration must be greater than 0 seconds") Integer durationSeconds,
			boolean previewFree) {
		super();
		this.title = title;
		this.description = description;
		this.videoUrl = videoUrl;
		this.lectueOder = lectueOder;
		this.durationSeconds = durationSeconds;
		this.previewFree = previewFree;
	}

	public LectureRequest() {
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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getLectueOder() {
		return lectueOder;
	}

	public void setLectueOder(Integer lectueOder) {
		this.lectueOder = lectueOder;
	}

	public Integer getDurationSeconds() {
		return durationSeconds;
	}

	public void setDurationSeconds(Integer durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	public boolean getPreviewFree() {
		return previewFree;
	}

	public void setPreviewFree(boolean previewFree) {
		this.previewFree = previewFree;
	}
	
	
}
