package com.example.compass.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	String uploadImage(MultipartFile file, String folder);
	
	String uploadVideo(MultipartFile file, String folder);

}
