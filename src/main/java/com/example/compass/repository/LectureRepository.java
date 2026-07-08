package com.example.compass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.compass.model.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture , Long> {

	List<Lecture> findBySectionIdOrderByLectureOrderAsc(Long sectionId);

}
