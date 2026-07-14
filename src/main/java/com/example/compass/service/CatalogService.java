package com.example.compass.service;

import org.springframework.data.domain.Page;

import com.example.compass.dto.CourseResponse;

public interface CatalogService {

//	List<CourseResponse> getPublishedCourses();
	Page<CourseResponse> getPublishedCourses(int page , int size , String sortBy , String direction , String keyword  , String category,String level);

}
