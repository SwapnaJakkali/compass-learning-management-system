package com.example.compass.serviceImps;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.compass.dto.CourseResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.mapper.CourseMapper;
import com.example.compass.model.Course;
import com.example.compass.repository.CourseRepository;
import com.example.compass.service.CatalogService;
import com.example.compass.specification.CourseSpecification;

@Service
public class CatalogServiceImpl implements CatalogService{

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;
	
	public CatalogServiceImpl(CourseRepository courseRepository,CourseMapper courseMapper) {
		this.courseRepository=courseRepository;
		this.courseMapper=courseMapper;
	}
	
	private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "title",
            "price",
            "createdAt",
            "level"
    );

    
	@Override
	public Page<CourseResponse> getPublishedCourses(
			int page ,
			int size ,
			String sortBy , 
			String direction , 
			String keyword,
			String category , 
			String level) {
		 if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
	            throw new IllegalArgumentException("Invalid sort field.");
	        }

	        // Sorting
	        Sort sort = direction.equalsIgnoreCase("asc")
	                ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();

	        // Pagination
	        Pageable pageable = PageRequest.of(page, size, sort);

		Specification<Course> specification = Specification.where(CourseSpecification.hasStatus(CourseStatus.PUBLISHED))
				.and(CourseSpecification.hasKeyword(keyword))
				.and(CourseSpecification.hasCategory(category))
				.and(CourseSpecification.hasLevel(level));
		Page<Course> courses=courseRepository.findAll(specification,pageable);

//		List<CourseResponse> response=new ArrayList<>();
//		for(Course c : courses) {
//			response.add(courseMapper.toResponse(c));
//		}
		return courses.map(courseMapper::toResponse);
	}
}
