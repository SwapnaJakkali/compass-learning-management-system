package com.example.compass.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.compass.enums.CourseStatus;
import com.example.compass.model.Course;
import com.example.compass.model.User;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> , JpaSpecificationExecutor<Course>{

//	List<Course> findByCourseStatus(CourseStatus courseStatus);
//	Page<Course> findByCourseStatus(CourseStatus courseStatus,
//				Pageable pageable);

	Page<Course> findByCourseStatusAndTitleContainingIgnoreCase(CourseStatus published, 
			String keyword , Pageable pageable);

	long countByInstructorAndCourseStatus(User instructor, CourseStatus published);

	long countByInstructor(User instructor);

	

}
