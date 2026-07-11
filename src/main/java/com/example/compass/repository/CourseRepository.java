package com.example.compass.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.compass.enums.CourseStatus;
import com.example.compass.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

//	List<Course> findByCourseStatus(CourseStatus courseStatus);
	Page<Course> findByCourseStatus(CourseStatus courseStatus,
				Pageable pageable);

}
