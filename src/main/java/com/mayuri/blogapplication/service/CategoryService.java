package com.mayuri.blogapplication.service;

import java.util.List;

import com.mayuri.blogapplication.payload.CategoryDto;


public interface CategoryService {
	
		// create
		public CategoryDto createCategory(CategoryDto categoryDto);
		
		//update
		public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto);
		
		//delete
		public void deleteCategory(Integer categoryId);
		
		//find user by id
		public CategoryDto getCategoryById(Integer categoryId);
		
		//Find all users
		public List<CategoryDto> getAllCategory();

}
