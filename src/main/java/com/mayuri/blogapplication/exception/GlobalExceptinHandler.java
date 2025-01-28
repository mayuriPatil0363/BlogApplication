package com.mayuri.blogapplication.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mayuri.blogapplication.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptinHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotfoundexceptionHandler(ResourceNotFoundException ex){
		ApiResponse response = new ApiResponse();
		response.setMessage(ex.getMessage());
		response.setSuccess(false);
		response.setStatusCode(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
		Map<String , String> responce = new HashMap<>();
		ex.getBindingResult()
		.getAllErrors()
		.forEach(error -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			responce.put(field, message);
		});
		
		return new ResponseEntity<>(responce , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadApiRequestException.class)
	public ResponseEntity<ApiResponse> badApiRequestExceptionHandler(BadApiRequestException ex){
		
		ApiResponse response = new ApiResponse();
		response.setMessage(ex.getMessage());
		response.setSuccess(false);
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
