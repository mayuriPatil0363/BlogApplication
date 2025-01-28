package com.mayuri.blogapplication.service;

import com.mayuri.blogapplication.payload.CommentDto;

public interface CommentService {
	
	CommentDto saveComment(CommentDto commentDto,Integer userId, Integer postId);
	
	void deleteComment(Integer commentId);

}
