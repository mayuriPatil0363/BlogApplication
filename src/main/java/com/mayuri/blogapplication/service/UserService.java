package com.mayuri.blogapplication.service;

import java.util.List;

import com.mayuri.blogapplication.payload.UserDto;

public interface UserService {
	
	// create
	public UserDto createUser(UserDto userDto);
	
	//update
	public UserDto updateUser(Integer userId, UserDto userDto);
	
	//delete
	public void deleteUser(Integer userId);
	
	//find user by id
	public UserDto getUserById(Integer userId);
	
	//Find all users
	public List<UserDto> getAllUsers();
	
	
	
	

}
