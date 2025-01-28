package com.mayuri.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayuri.blogapplication.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
