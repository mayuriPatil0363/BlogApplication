package com.mayuri.blogapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuri.blogapplication.exception.ResourceNotFoundException;
import com.mayuri.blogapplication.model.Category;
import com.mayuri.blogapplication.payload.CategoryDto;
import com.mayuri.blogapplication.repository.CategoryRepo;
import com.mayuri.blogapplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	//create category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto)
	{
		Category category = mapper.map(categoryDto, Category.class);
		Category savedCategory = categoryRepo.save(category);
		return mapper.map(savedCategory, CategoryDto.class);
	}
	
	//update category
	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) 
	{
		Category category =categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId));
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		Category updatedCategory = categoryRepo.save(category);
		
		return mapper.map(updatedCategory, CategoryDto.class);
	}

	//delete category
	@Override
	public void deleteCategory(Integer categoryId) 
	{
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId", categoryId));
		categoryRepo.delete(category);
	}

	//find category by id
	@Override
	public CategoryDto getCategoryById(Integer categoryId) 
	{
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		return mapper.map(category, CategoryDto.class);
	}

	//find list of categories
	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		logger.info("List of categories: {}", categories);

		List<CategoryDto> categoryDto = categories.stream().map(category -> mapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
		logger.info("List of categories: {}", categoryDto);
		return categoryDto;
	}
	
	

}
