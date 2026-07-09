package com.example.compass.serviceImps;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.compass.dto.SectionRequest;
import com.example.compass.dto.SectionResponse;
import com.example.compass.exception.CourseNotFoundException;
import com.example.compass.exception.SectionNotFoundException;
import com.example.compass.exception.UnauthorizedCourseAccessException;
import com.example.compass.model.Course;
import com.example.compass.model.Section;
import com.example.compass.model.User;
import com.example.compass.repository.CourseRepository;
import com.example.compass.repository.SectionRepository;
import com.example.compass.service.CurrentUserService;
import com.example.compass.service.SectionService;

import jakarta.validation.Valid;

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
				.orElseThrow(()->new CourseNotFoundException("Course not Found"));
		User currentUser = currentUserService.getCurrentUser();
		if(!currentUser.getId().equals(course.getInstructor().getId())) {
			throw new UnauthorizedCourseAccessException("You are not authorized to modify this course");
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
				.orElseThrow(()->new CourseNotFoundException("Course not Found"));
		List<Section> sections =  sectionRepository.findByCourseIdOrderBySectionOrderAsc(courseId);
		return sections.stream()
				.map(s->mapToResponse(s))
				.toList();
	}

	@Override
	public SectionResponse updateSection(Long sectionId, @Valid SectionRequest request) {
		
		Section section=sectionRepository.findById(sectionId)
		.orElseThrow(()->new SectionNotFoundException("Section not found"));
		
		User currentUser = currentUserService.getCurrentUser();
		if(!currentUser.getId().equals(section.getCourse().getInstructor().getId())) {
			throw new UnauthorizedCourseAccessException("You are not authorized to modify this course");
		}
		
		section.setUpdatedAt(LocalDateTime.now());
		section.setTitle(request.getTitle());
		section.setDescription(request.getDescription());
		section.setSectionOrder(request.getSectionOrder());
		
		Section updatedSection = sectionRepository.save(section);
		return mapToResponse(updatedSection);
	}

	@Override
	public void deleteSection(Long sectionId) {

		Section section = sectionRepository.findById(sectionId)
				.orElseThrow(()-> new SectionNotFoundException("Section Not Found"));
		User currentUser=currentUserService.getCurrentUser();
		if(!currentUser.getId().equals(section.getCourse().getInstructor().getId())) {
			throw new UnauthorizedCourseAccessException("You are not authorized to modify this course");
		}
		
		sectionRepository.delete(section);
	}
	

}
