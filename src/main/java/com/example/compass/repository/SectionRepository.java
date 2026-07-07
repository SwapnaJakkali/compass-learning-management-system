package com.example.compass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.compass.model.Section;

public interface SectionRepository extends JpaRepository<Section,Long>{
	
	List<Section> findByCourseIdOrderBySectionOrderAsc(Long courseId);

}
