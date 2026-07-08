package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.compass.dto.LectureRequest;
import com.example.compass.dto.LectureResponse;
import com.example.compass.exception.SectionNotFoundException;
import com.example.compass.exception.UnauthorizedCourseAccessException;
import com.example.compass.model.Course;
import com.example.compass.model.Lecture;
import com.example.compass.model.Section;
import com.example.compass.model.User;
import com.example.compass.repository.LectureRepository;
import com.example.compass.repository.SectionRepository;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService{

	private final LectureRepository lectureRepository;
	private final SectionRepository sectionRepository;
	private final CurrentUserService currentUserService;
	
	public LectureServiceImpl(LectureRepository lectureRepository,
			SectionRepository sectionRepository,
			CurrentUserService currentUserService) {
		this.lectureRepository=lectureRepository;
		this.currentUserService=currentUserService;
		this.sectionRepository=sectionRepository;
	}
	private LectureResponse mapToResponse(Lecture lecture) {
		LectureResponse response = new LectureResponse();
		
		response.setDescription(lecture.getDescription());
		response.setDurationInSeconds(lecture.getDurationSeconds());
		response.setTitle(lecture.getTitle());
		response.setLectureOrder(lecture.getLectureOrder());
		response.setVideoUrl(lecture.getVideoUrl());
		response.setId(lecture.getId());
		response.setPreviewFree(lecture.getPreviewFree());
		
		return response;
	}
	
	@Override
	public LectureResponse createLecture(Long sectionId, LectureRequest request) {
		Section section = sectionRepository.findById(sectionId)
				.orElseThrow(()->new SectionNotFoundException("Section not found"));
		User currentUser=currentUserService.getCurrentUser();
		
		Course course = section.getCourse();

		if (!currentUser.getId().equals(course.getInstructor().getId())) {
		    throw new UnauthorizedCourseAccessException("You are not allowed to modify this course.");
		}
//		if(!currentUser.getId().equals(section.getCourse().getInstructor().getId())) {
//			throw new UnauthorizedCourseAccessException("You are not allowed to modify this course.");
//		}
		
		Lecture lecture = new Lecture();
		lecture.setTitle(request.getTitle());
		lecture.setDescription(request.getDescription());
		lecture.setLectureOrder(request.getLectueOder());
		lecture.setDurationSeconds(request.getDurationSeconds());
		lecture.setPreviewFree(request.getPreviewFree());
		lecture.setVideoUrl(request.getVideoUrl());
		lecture.setSection(section);
		
		LocalDateTime now = LocalDateTime.now();
		lecture.setCreatedAt(now);
		lecture.setUpdatedAt(now);
		
		Lecture savedLecture=lectureRepository.save(lecture);
		return mapToResponse(savedLecture);
	}
	@Override
	public List<LectureResponse> getLectureBysection(Long sectionId) {
		sectionRepository.findById(sectionId)
				.orElseThrow(()->new SectionNotFoundException("Section not found"));
		List<Lecture> lectures = lectureRepository.findBySectionIdOrderByLectureOrderAsc(sectionId);
		
		return lectures.stream()
				.map(this::mapToResponse)
				.toList();		
	}

}
