package com.example.compass.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.compass.model.Enrollment;
import com.example.compass.model.User;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{

	boolean existsByStudentIdAndCourseId(Long id, Long courseId);
	
	List<Enrollment> findByStudentId(Long studentId);

	List<Enrollment> findByCourseId(Long courseId);

	long countByCourseInstructor(User instructor);

	long countByStudent(User student);

	Page<Enrollment> findByStudent(User student, Pageable pageable);
}
