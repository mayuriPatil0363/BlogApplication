package com.mayuri.blogapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuri.blogapplication.exception.ResourceNotFoundException;
import com.mayuri.blogapplication.model.User;
import com.mayuri.blogapplication.payload.UserDto;
import com.mayuri.blogapplication.repository.UserRepo;
import com.mayuri.blogapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;
	

	//Create user
	@Override
	public UserDto createUser(UserDto userDto)
	{
		User user = mapper.map(userDto, User.class);
		User savedUser = userRepo.save(user);
		logger.info("user : {}", savedUser);
		UserDto map = mapper.map(savedUser, UserDto.class);
		logger.info("userDto : {}", map);
		return map;
	}

	//update user
	@Override
	public UserDto updateUser(Integer userId, UserDto userDto)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId" ,userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = userRepo.save(user);
		return mapper.map(updatedUser, UserDto.class);
		
	}

	//delete user
	@Override
	public void deleteUser(Integer userId)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId" ,userId ));
		userRepo.delete(user);
			
	}

	//find user by user id
	@Override
	public UserDto getUserById(Integer userId)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId" ,userId));
		return mapper.map(user, UserDto.class);
	}

	//find all users list
	@Override
	public List<UserDto> getAllUsers()
	{
		List<User> users = userRepo.findAll();
		return users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

}
