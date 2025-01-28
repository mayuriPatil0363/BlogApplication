package com.mayuri.blogapplication.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuri.blogapplication.exception.ResourceNotFoundException;
import com.mayuri.blogapplication.model.Comment;
import com.mayuri.blogapplication.model.Post;
import com.mayuri.blogapplication.model.User;
import com.mayuri.blogapplication.payload.CommentDto;
import com.mayuri.blogapplication.repository.CommentRepo;
import com.mayuri.blogapplication.repository.PostRepo;
import com.mayuri.blogapplication.repository.UserRepo;
import com.mayuri.blogapplication.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto saveComment(CommentDto commentDto, Integer userId, Integer postId)
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
		
		Comment comment = mapper.map(commentDto, Comment.class);
		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment = commentRepo.save(comment);
		
		return mapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId)
	{
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId",commentId));
		commentRepo.delete(comment);
	}

}
