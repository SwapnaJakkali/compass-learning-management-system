package com.example.compass.service;

import java.util.List;

import com.example.compass.dto.SectionRequest;
import com.example.compass.dto.SectionResponse;

import jakarta.validation.Valid;

public interface SectionService {
	SectionResponse createSection(Long courseId,SectionRequest request);
	
	List<SectionResponse> getSectionsByCourse(Long courseId);

	SectionResponse updateSection(Long sectionId, @Valid SectionRequest request);

	void deleteSection(Long sectionId);
}
