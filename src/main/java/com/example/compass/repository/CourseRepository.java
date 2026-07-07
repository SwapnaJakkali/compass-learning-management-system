package com.example.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.compass.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
