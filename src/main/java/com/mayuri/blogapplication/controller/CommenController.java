package com.mayuri.blogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mayuri.blogapplication.payload.ApiResponse;
import com.mayuri.blogapplication.payload.CommentDto;
import com.mayuri.blogapplication.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommenController {
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping(value="/save")
	public ResponseEntity<CommentDto> saveComment( @RequestBody CommentDto commentDto,@RequestParam Integer userId,@RequestParam Integer postId){
		
		return new ResponseEntity<>(commentService.saveComment(commentDto, userId, postId),HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value ="/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(Integer commentId)
	{
		commentService.deleteComment(commentId);
		
		ApiResponse responce = new ApiResponse();
		responce.setMessage("comment deleted successfully !!");
		responce.setSuccess(true);
		
		return new ResponseEntity<>(responce, HttpStatus.OK);
		
	}

}
