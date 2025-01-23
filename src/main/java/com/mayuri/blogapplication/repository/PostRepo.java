package com.mayuri.blogapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayuri.blogapplication.model.Category;
import com.mayuri.blogapplication.model.Post;
import com.mayuri.blogapplication.model.User;

public interface PostRepo  extends JpaRepository<Post,Integer>{
	
	List<Post> findByCategory(Category category);
		
	List<Post> findByUser(User user);
	
	List<Post> findByTitleContaining(String Key);

}
