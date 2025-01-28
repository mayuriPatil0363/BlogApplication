package com.mayuri.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuri.blogapplication.payload.ApiResponse;
import com.mayuri.blogapplication.payload.UserDto;
import com.mayuri.blogapplication.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create user
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto user = userService.createUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	//update user
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId,@Valid @RequestBody UserDto userDto)
	{
		UserDto user = userService.updateUser(userId, userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	} 
	
	//delete user
	//@PreAuthorize("hasRole('Role_Admin')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		userService.deleteUser(userId);
		ApiResponse response = new ApiResponse();
		response.setMessage("User Deleted successfully !!");
		response.setSuccess(true);
		response.setStatusCode(HttpStatus.OK);
		return new ResponseEntity<>(response , HttpStatus.OK);
		
	}
	
//	//delete
//	@DeleteMapping("/delete/{userId}")
//	public ResponseEntity<?> deleteUser1(Integer userId){
//		userService.deleteUser(userId);
//		return new ResponseEntity<>(Map.of("message","User Deleted successfully"), HttpStatus.OK);
//	}
	
	//find user by id
	@GetMapping("/byId/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
	{
		UserDto userById = userService.getUserById(userId);
		return new ResponseEntity<>(userById, HttpStatus.OK);
	}
	
	//find all users list
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

}
