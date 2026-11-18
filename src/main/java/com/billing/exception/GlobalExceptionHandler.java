package com.billing.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationException(
	            MethodArgumentNotValidException ex) {	    	
	    	
	    	 Map<String, String> errors = new HashMap<>();

	    	    ex.getBindingResult()
	    	            .getFieldErrors()
	    	            .forEach(error ->
	    	                    errors.put(
	    	                            error.getField(),
	    	                            error.getDefaultMessage()
	    	                    )
	    	            );
	    	    return ResponseEntity.badRequest().body(errors);
	    	}
	    
	    
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {	    	
	    	
	        Map<String, String> errors = new HashMap<>();

	        String message = ex.getMostSpecificCause().getMessage();

	        if (message.contains("employee_id")) {
	            errors.put("employeeId", "Employee ID already exists.");
	        } else if (message.contains("email")) {
	            errors.put("email", "Email already exists.");
	        } else {
	            errors.put("error", "Duplicate value found.");
	        }
	        return ResponseEntity.badRequest().body(errors);
	    }
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> badCredentialsException(BadCredentialsException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	}
	
//	************************* Forbidden 403 ****************************************************************
	
	@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(
            AccessDeniedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("You do not have permission to access this resource. ");
    }
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(
    		RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
