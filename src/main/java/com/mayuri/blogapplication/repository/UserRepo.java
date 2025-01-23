package com.mayuri.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mayuri.blogapplication.model.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

}
