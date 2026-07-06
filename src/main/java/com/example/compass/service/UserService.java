package com.example.compass.service;

import com.example.compass.dto.LoginRequest;
import com.example.compass.dto.LoginResponse;
import com.example.compass.dto.RegisterRequest;

public interface UserService {

	void register(RegisterRequest request) throws RuntimeException;

	LoginResponse login(LoginRequest request) throws RuntimeException;

}
