package com.example.compass.serviceImps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.example.compass.exception.FileUploadException;
import com.example.compass.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;

    public FileUploadServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    
    
    private void validateImage(MultipartFile file) {

        if (file.isEmpty()) {
            throw new FileUploadException("Please select a file.");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.equals("image/jpeg")
                || contentType.equals("image/png")
                || contentType.equals("image/webp"))) {

            throw new FileUploadException(
                    "Only JPG, PNG and WEBP images are allowed.");
        }

        long maxSize = 5 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new FileUploadException(
                    "File size should not exceed 5 MB.");
        }
    }
    
    
    private void validateVideo(MultipartFile file) {

        if (file.isEmpty()) {
            throw new FileUploadException("Please select a video.");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !contentType.equals("video/mp4")) {

            throw new FileUploadException(
                    "Only MP4 videos are allowed.");
        }

        long maxSize = 100 * 1024 * 1024; // 100 MB

        if (file.getSize() > maxSize) {
            throw new FileUploadException(
                    "Video size should not exceed 100 MB.");
        }
    }

    @Override
    public String uploadImage(MultipartFile file , String folder) {

    		validateImage(file);
        
    		return uploadToCloudinary(file,folder);
    }

    @Override
    public String uploadVideo(MultipartFile file, String folder) {

        validateVideo(file);

        return uploadToCloudinary(file, folder);
    }
    
    
    private String uploadToCloudinary(MultipartFile file , String folder) {
    			try {
        	
        	
        	Map<String, Object> options = new HashMap<>();

        	options.put("folder", folder);
            Map<String, Object> uploadResult =
                    cloudinary.uploader().upload(
                            file.getBytes(),
                            options
                    );

            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
        		throw new FileUploadException(
        	        "Failed to upload file to Cloudinary.");
        }
    }

	

}