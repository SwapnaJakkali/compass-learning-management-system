package com.example.compass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

	@GetMapping("/helo")
	public String getMethodName() {
		return "helo from instructor";
	}
	
}
