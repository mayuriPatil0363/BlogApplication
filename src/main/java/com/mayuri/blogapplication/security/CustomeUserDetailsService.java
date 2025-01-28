package com.mayuri.blogapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mayuri.blogapplication.exception.ResourceNotFoundException;
import com.mayuri.blogapplication.model.User;
import com.mayuri.blogapplication.repository.UserRepo;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","userId",username));
		return user;
	}

}
