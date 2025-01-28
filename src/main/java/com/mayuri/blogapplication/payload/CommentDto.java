package com.mayuri.blogapplication.payload;

import com.mayuri.blogapplication.model.Post;
import com.mayuri.blogapplication.model.User;

import jakarta.validation.constraints.NotBlank;


public class CommentDto {
	
		
	private Integer commentId;
	
	@NotBlank
	private String content;
	
	private PostDto post;
	
	private UserDto user;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PostDto getPost() {
		return post;
	}

	public void setPost(PostDto post) {
		this.post = post;
	}

		
	

}
