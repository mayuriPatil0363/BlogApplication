package com.mayuri.blogapplication.payload;

import java.util.ArrayList;
import java.util.List;

import com.mayuri.blogapplication.model.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=2, max=20, message="Size must be bitween 2 and 20. ")
	private String Title;

	@NotBlank
	@Size(min=2, max=500, message="Size must be bitween 2 and 500.")
	private String description;
	
	
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", Title=" + Title + ", description=" + description + "]";
	}

	

	
	
}
