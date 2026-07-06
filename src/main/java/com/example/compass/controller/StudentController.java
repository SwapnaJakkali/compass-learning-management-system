package com.example.compass.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@GetMapping("/helo")
	public String hello() {
	    return "Hello Student";
	}
}
