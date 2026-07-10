package com.example.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.compass.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{

	boolean existsByStudentIdAndCourseId(Long id, Long courseId);

}
