package com.mayuri.blogapplication.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	private Integer postId;
	
	@Column(name="post_Title")
	private String title;
	
	@Column(name="post_content",length=50000)
	private String content;
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="posted_date")
	private Date date;
	
	private Boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="userId_fk")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="categoryId_fk")
	private Category category;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Comment> comment = new HashSet();

	
	
	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", date=" + date + ", isActive=" + isActive + ", user=" + user + ", category=" + category + "]";
	}


	

	
	
	

	
	
	

}
