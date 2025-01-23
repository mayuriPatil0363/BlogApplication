package com.mayuri.blogapplication.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	
	private String message;
	private Boolean success;
	private HttpStatus statusCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", success=" + success + ", statusCode=" + statusCode + "]";
	}
		
	

}
