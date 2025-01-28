package com.mayuri.blogapplication.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mayuri.blogapplication.constant.PostConstant;
import com.mayuri.blogapplication.exception.ResourceNotFoundException;
import com.mayuri.blogapplication.model.Category;
import com.mayuri.blogapplication.model.Post;
import com.mayuri.blogapplication.model.User;
import com.mayuri.blogapplication.payload.CategoryDto;
import com.mayuri.blogapplication.payload.PageableResponse;
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

	//create post
	@Override
	public PostDto createPost(Integer userId, Integer categoryId ,PostDto postDto)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post = mapper.map(postDto, Post.class);
		post.setUser(user);
		post.setIsActive(PostConstant.IS_ACTIVE_TRUE);
		post.setCategory(category);
		post.setImageName("default.png");
		post.setDate(new Date());
		Post savedPost = postRepo.save(post);
		return mapper.map(savedPost, PostDto.class);
	}

	
	//update post
	@Override
	public PostDto updatePost(Integer postId, PostDto postDto)
	{
		Post post = postRepo.findById(postId).orElseThrow(()->new  ResourceNotFoundException("Post","postId",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = postRepo.save(post);
		
		return mapper.map(updatedPost, PostDto.class);
	}

	
	//delete post
	@Override
	public void deletePost(Integer postId) 
	{
		Post post =postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));
		if(!ObjectUtils.isEmpty(post)) {
			post.setIsActive(PostConstant.IS_ACTIVE_FALSE);
			postRepo.save(post);
		}
	
	}

	
	//find by postId
	@Override
	public PostDto getPostById(Integer postId) 
	{
		Post post =postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));
		return mapper.map(post, PostDto.class);
	}

	
	//find all posts
	@Override
	public PageableResponse getListOfPosts(Integer pageNumber, Integer pageSize, String sortBy , String sortDir)
	{
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
		PageRequest pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> page = postRepo.findAll(pageable);
		List<Post> posts = page.getContent();
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PageableResponse response = new PageableResponse();
		response.setContent(collect);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElement(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setIsLastpage(page.isLast());
		return response ;
	}

	
	//find post by category
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId)
	{
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
		
	}

	
	//find post by user
	@Override
	public List<PostDto> getPostByUserId(Integer userId)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	
	//search post
	@Override
	public List<PostDto> searchPost(String key) {
		List<Post> posts = postRepo.findByTitleContaining(key);
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
		
	}


}
