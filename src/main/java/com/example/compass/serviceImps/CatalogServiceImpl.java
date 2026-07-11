package com.example.compass.serviceImps;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.compass.dto.CourseResponse;
import com.example.compass.enums.CourseStatus;
import com.example.compass.mapper.CourseMapper;
import com.example.compass.model.Course;
import com.example.compass.repository.CourseRepository;
import com.example.compass.service.CatalogService;

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
	public Page<CourseResponse> getPublishedCourses(int page , int size , String sortBy , String direction) {

		if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
	        throw new IllegalArgumentException("Invalid sort field.");
	    }
	    
		Sort sort=direction.equalsIgnoreCase("asc")
				?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(page, size , sort);

		Page<Course> courses = courseRepository.findByCourseStatus(CourseStatus.PUBLISHED,pageable);
//		List<CourseResponse> response=new ArrayList<>();
//		for(Course c : courses) {
//			response.add(courseMapper.toResponse(c));
//		}
		return courses.map(courseMapper::toResponse);
	}

}
