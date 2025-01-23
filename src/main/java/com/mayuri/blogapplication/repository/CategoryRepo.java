package com.mayuri.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayuri.blogapplication.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
