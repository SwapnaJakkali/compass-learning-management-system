package com.example.compass.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compass.dto.LoginRequest;
import com.example.compass.dto.RegisterRequest;
import com.example.compass.dto.RegisterResponse;
import com.example.compass.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
    	userService.register(request);
    	return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponse("User registered successfully", request.getEmail()));
    }
    
    @PostMapping("/verify")
    public String postMethodName(@Valid @RequestBody LoginRequest request) {
        //TODO: process POST request
        userService.login(request);
        return "logged in";
    }
    
}