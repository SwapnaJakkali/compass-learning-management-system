package com.example.compass.specification;


import org.springframework.data.jpa.domain.Specification;

import com.example.compass.enums.CourseStatus;
import com.example.compass.model.Course;

public class CourseSpecification {
	
	private CourseSpecification() {
		
	}
	
	public static Specification<Course> hasStatus(CourseStatus status){
		return (root,query,cb)->cb.equal(root.get("courseStatus"),status);
	}
	public static Specification<Course> hasKeyword(String keyword){
		if(keyword==null||keyword.isBlank()) {
			return (root,query,cb)->cb.conjunction();
		}
		return (root, query , cb)->
		cb.or(
			    cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"),
			    cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%")
			);
			
	}

	    /**
	     * Filter by Category
	     */
	    public static Specification<Course> hasCategory(String category) {

	        if (category == null || category.isBlank()) {
	            return (root, query, cb) -> cb.conjunction();
	        }

	        return (root, query, cb) ->
	                cb.equal(
	                        cb.lower(root.get("category")),
	                        category.toLowerCase()
	                );
	    }

	    /**
	     * Filter by Level
	     */
	    public static Specification<Course> hasLevel(String level) {

	        if (level == null || level.isBlank()) {
	            return (root, query, cb) -> cb.conjunction();
	        }

	        return (root, query, cb) ->
	                cb.equal(
	                        cb.lower(root.get("level")),
	                        level.toLowerCase()
	                );
	    }
	
}
