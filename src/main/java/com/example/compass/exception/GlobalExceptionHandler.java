package com.example.compass.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.compass.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private ErrorResponse buildErrorResponse(
				HttpStatus status ,
				String message,
				HttpServletRequest request) {
		return new ErrorResponse(
				LocalDateTime.now(),
				status.value(),
				status.getReasonPhrase(),
				message,
				request.getRequestURI());
	}

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
    public ResponseEntity<ErrorResponse> handleCourseNotFoundException(CourseNotFoundException e,
    											HttpServletRequest request){
    		ErrorResponse response=buildErrorResponse(
					HttpStatus.NOT_FOUND,
					e.getMessage(),
					request);
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(response);
    }
    
    
    @ExceptionHandler(UnauthorizedCourseAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedCourseAccessException(UnauthorizedCourseAccessException e,
    		HttpServletRequest request){
    		
    		ErrorResponse response=buildErrorResponse(
    				HttpStatus.FORBIDDEN,
    				e.getMessage(),
    				request);
    		
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
    
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAcessDeniedException(org.springframework.security.access.AccessDeniedException e,
    		HttpServletRequest request){

    		ErrorResponse response=buildErrorResponse(
    				HttpStatus.FORBIDDEN,
    				"You are not authorized to perform this action.",
    				request);
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex,HttpServletRequest request) {
      
    		ErrorResponse response = buildErrorResponse(
    				HttpStatus.INTERNAL_SERVER_ERROR,
    				"Something went wrong",
    				request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  
    }
    
}