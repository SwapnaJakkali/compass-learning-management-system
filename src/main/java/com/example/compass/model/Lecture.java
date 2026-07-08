package com.example.compass.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="lectures")
public class Lecture {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(length=1000)
	private String description;
	
	private String videoUrl;
	
	@Column(name="lecture_order",nullable=false)
	private Integer lectureOrder;
	
	@Column(nullable=false)
	private Integer durationSeconds;
	
	@Column(nullable=false)
	private boolean previewFree;
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@Column(nullable=false)
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="section_id",nullable=false)
	private Section section;

	public Lecture(Long id, String title, String description,String videoUrl, Integer lectureOrder, Integer durationSeconds,
			boolean previewFree, LocalDateTime createdAt, LocalDateTime updatedAt, Section section) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.lectureOrder = lectureOrder;
		this.durationSeconds = durationSeconds;
		this.previewFree = previewFree;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.section = section;
		this.videoUrl=videoUrl;
	}

	public Lecture() {
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

	public Integer getLectureOrder() {
		return lectureOrder;
	}

	public void setLectureOrder(Integer lectureOrder) {
		this.lectureOrder = lectureOrder;
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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	
	
}
