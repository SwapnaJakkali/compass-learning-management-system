package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.compass.dto.SectionRequest;
import com.example.compass.dto.SectionResponse;
import com.example.compass.model.Course;
import com.example.compass.model.Section;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.repository.SectionRepository;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService{

	private final SectionRepository sectionRepository;
	private final CourseRepository courseRepository;
	private final CurrentUserService currentUserService;
	
	public SectionServiceImpl(SectionRepository sectionRepository,CourseRepository courseRepository,CurrentUserService currentUserService) {
		this.courseRepository=courseRepository;
		this.currentUserService=currentUserService;
		this.sectionRepository=sectionRepository;
	}
	
	private SectionResponse mapToResponse(Section section) {
		SectionResponse response = new SectionResponse();
		
		response.setId(section.getId());
		response.setTitle(section.getTitle());
		response.setDescription(section.getDescription());
		response.setSectionOrder(section.getSectionOrder());
		return response;
	}
	
	@Override
	public SectionResponse createSection(Long courseId, SectionRequest request) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(()->new RuntimeException("Course not found"));
		User currentUser = currentUserService.getCurrentUser();
		if(!currentUser.getId().equals(course.getInstructor().getId())) {
			throw new RuntimeException("You are not authorized to modify this course");
		}
		
		Section section = new Section();

		section.setTitle(request.getTitle());
		section.setDescription(request.getDescription());
		section.setSectionOrder(request.getSectionOrder());
		LocalDateTime now = LocalDateTime.now();
		section.setCreatedAt(now);
		section.setUpdatedAt(now);
		section.setCourse(course);
		
		Section savedSection = sectionRepository.save(section);
		
		return mapToResponse(savedSection);
	}

	@Override
	public List<SectionResponse> getSectionsByCourse(Long courseId) {
		courseRepository.findById(courseId)
				.orElseThrow(()->new RuntimeException("Course not found"));
		List<Section> sections =  sectionRepository.findByCourseIdOrderBySectionOrderAsc(courseId);
		return sections.stream()
				.map(s->mapToResponse(s))
				.toList();
		
	}
}
