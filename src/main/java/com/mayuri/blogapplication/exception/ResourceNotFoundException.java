package com.mayuri.blogapplication.exception;

public class ResourceNotFoundException extends RuntimeException 
{
	
	private String resourseName;
	private String fieldName;
	private Integer fieldId;
	
	
	
	public ResourceNotFoundException(String resourseName, String fieldName, Integer fieldId) {
		super(String.format("%s not found with %s : %s",resourseName ,fieldName ,fieldId ));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	
	
	
}
