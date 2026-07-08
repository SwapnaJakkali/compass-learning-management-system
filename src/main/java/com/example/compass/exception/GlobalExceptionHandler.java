package com.example.compass.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (this will fix your 401 issue)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);  // Returns 400 Bad Request
    }
    
    
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Map<String , String>> handleCourseNotFoundException(CourseNotFoundException e){
    		Map<String,String> error=new HashMap<>();
    		error.put("error", e.getMessage());
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    
    @ExceptionHandler(UnauthorizedCourseAccessException.class)
    public ResponseEntity<Map<String , String>> handleUnauthorizedCourseAccessException(UnauthorizedCourseAccessException e){
    		Map<String,String> error=new HashMap<>();
    		error.put("error", e.getMessage());
    		
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
    
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Map<String , String>> handleAcessDeniedException(org.springframework.security.access.AccessDeniedException e){
    		Map<String , String> err=new HashMap<>();
    		err.put("error","You are not authorized to perform this action." );
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);  
    }
    
}