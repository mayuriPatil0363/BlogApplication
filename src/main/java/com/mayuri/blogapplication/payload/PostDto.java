package com.mayuri.blogapplication.payload;

import java.util.Date;

import com.mayuri.blogapplication.model.Category;
import com.mayuri.blogapplication.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostDto {

	private Integer postId;
	
	@NotBlank
	@Size(min=5, message="Title size must be more than 5 characters")
	private String title;
	
	@NotBlank
	private String content;
	
	private String imageName;
	
	
	private UserDto user;
	
	private CategoryDto category;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImage(String imageName) {
		this.imageName = imageName;
	}	

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", user=" + user + ", category=" + category + "]";
	}

	
	
	
	
	
	
	
	
}
