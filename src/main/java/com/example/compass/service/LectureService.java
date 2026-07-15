package com.example.compass.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.compass.dto.LectureRequest;
import com.example.compass.dto.LectureResponse;

import jakarta.validation.Valid;

public interface LectureService {

	LectureResponse createLecture(Long sectionId, LectureRequest request);

	List<LectureResponse> getLectureBysection(Long sectionId);

	LectureResponse updateLecture(Long lectureId, @Valid LectureRequest request);

	void deleteLecture(Long lectureId);
	
	LectureResponse updateVideo(Long lectureId, MultipartFile file);

}
