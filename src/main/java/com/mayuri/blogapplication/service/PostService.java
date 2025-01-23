package com.mayuri.blogapplication.service;

import java.util.List;

import com.mayuri.blogapplication.payload.PostDto;

public interface PostService {

	//create post
	PostDto createPost(Integer userId, Integer categoryId ,PostDto postDto);
	
	//update
	PostDto updatePost(Integer postId, PostDto postDto);
	
	//delete
	void deletePost(Integer postId);
	
	//find post by post id
	PostDto getPostById(Integer postId);
	
	//list of posts
	List<PostDto> getListOfPosts();
	
	//find posts by category id
	List<PostDto> getPostByCategory(Integer categoryId);
	
	// find post by user id
	List<PostDto> getPostByUserId(Integer userId);
	
	//find post with given matches
	List<PostDto> searchPost(String key);
}
