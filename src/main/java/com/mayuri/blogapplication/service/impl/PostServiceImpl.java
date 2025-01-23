package com.mayuri.blogapplication.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuri.blogapplication.exception.ResourceNotFoundException;
import com.mayuri.blogapplication.model.Category;
import com.mayuri.blogapplication.model.Post;
import com.mayuri.blogapplication.model.User;
import com.mayuri.blogapplication.payload.CategoryDto;
import com.mayuri.blogapplication.payload.PostDto;
import com.mayuri.blogapplication.repository.CategoryRepo;
import com.mayuri.blogapplication.repository.PostRepo;
import com.mayuri.blogapplication.repository.UserRepo;
import com.mayuri.blogapplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public PostDto createPost(Integer userId, Integer categoryId ,PostDto postDto)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post = mapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setImageName("default.png");
		post.setDate(new Date());
		Post savedPost = postRepo.save(post);
		return mapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(Integer postId, PostDto postDto)
	{
		Post post = postRepo.findById(postId).orElseThrow(()->new  ResourceNotFoundException("Post","postId",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost = postRepo.save(post);
		
		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) 
	{
		Post post =postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));
		postRepo.delete(post);
	
	}

	@Override
	public PostDto getPostById(Integer postId) 
	{
		Post post =postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));
		return mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getListOfPosts()
	{
		List<Post> posts = postRepo.findAll();
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId)
	{
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	
		
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> searchPost(String key) {
		List<Post> posts = postRepo.findByTitleContaining(key);
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
		
	}

}
