package com.mayuri.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuri.blogapplication.payload.ApiResponse;
import com.mayuri.blogapplication.payload.CategoryDto;
import com.mayuri.blogapplication.service.CategoryService;

import jakarta.validation.Valid; 


@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer categoryId, @Valid @RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(categoryService.updateCategory(categoryId, categoryDto), HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
	{
		categoryService.deleteCategory(categoryId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Category deleted successfully !!");
		response.setSuccess(true);
		response.setStatusCode(HttpStatus.OK);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// find by id
	@GetMapping("/byId/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId)
	{
		return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
	}
	
	//get category list
	@GetMapping("/listOfAllCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
	}
	

}
