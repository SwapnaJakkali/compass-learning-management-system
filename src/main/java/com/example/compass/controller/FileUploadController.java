package com.example.compass.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.compass.service.FileUploadService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequestMapping("/api/upload")
@RestController
@SecurityRequirement(name = "bearerAuth")
public class FileUploadController {
	private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
    
    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file ,
    								@RequestParam("folder")String folder) {
        //TODO: process POST request
        String url=fileUploadService.uploadImage(file,folder);
        return ResponseEntity.ok(url);
    }
    
}
