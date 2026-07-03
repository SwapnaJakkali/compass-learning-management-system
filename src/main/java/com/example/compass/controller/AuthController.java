package com.example.compass.controller;

import org.springframework.web.bind.annotation.*;

import com.example.compass.dto.RegisterRequest;
import com.example.compass.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }
}