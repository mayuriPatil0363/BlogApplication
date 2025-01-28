package com.mayuri.blogapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mayuri.blogapplication.model.User;
import com.mayuri.blogapplication.payload.UserDto;

public interface UserRepo  extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);

}
