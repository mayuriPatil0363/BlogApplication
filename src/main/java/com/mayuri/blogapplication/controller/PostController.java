package com.mayuri.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.mayuri.blogapplication.payload.PostDto;
import com.mayuri.blogapplication.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//Create Post
	@PostMapping("/create/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@PathVariable Integer userId,@PathVariable Integer categoryId,@Valid @RequestBody PostDto postDto)
	{
		return new ResponseEntity<>(postService.createPost(userId, categoryId, postDto),HttpStatus.CREATED);
	}
	
	
	//update post
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId,@Valid @RequestBody PostDto postdto){
		return new ResponseEntity<>(postService.updatePost(postId, postdto),HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		postService.deletePost(postId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Post deleted successfully !!");
		response.setSuccess(true);
		response.setStatusCode(HttpStatus.OK);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//find post by postId
	@GetMapping("/byId/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
	}
	
	//find list of all posts
	@GetMapping("/allPosts")
	public ResponseEntity<List<PostDto>> getListOfPosts()
	{
		return new ResponseEntity<>(postService.getListOfPosts(),HttpStatus.OK);
	}
	
	//find posts by userId
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getAllPostByUserId(@PathVariable Integer userId)
	{
		return new ResponseEntity<>(postService.getPostByUserId(userId),HttpStatus.OK);
	}
	
	//find posts by categoryId
		@GetMapping("/category/{categoryId}")
		public ResponseEntity<List<PostDto>> getAllPostByCategoryId(@PathVariable Integer categoryId)
		{
			return new ResponseEntity<>(postService.getPostByCategory(categoryId),HttpStatus.OK);
		}
		
		//find post using title containt
		@GetMapping("key/{key}")
		public ResponseEntity<List<PostDto>>  searchPost(@PathVariable String key)
		{
			return new ResponseEntity<>(postService.searchPost(key),HttpStatus.OK);
			
		}

}
