package com.example.compass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.compass.dto.StudentResponse;
import com.example.compass.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{

	boolean existsByStudentIdAndCourseId(Long id, Long courseId);
	
	List<Enrollment> findByStudentId(Long studentId);

	List<Enrollment> findByCourseId(Long courseId);

}
