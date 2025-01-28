package com.mayuri.blogapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mayuri.blogapplication.repository.UserRepo;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	 void userRepoProxyClass() {
		String canonicalName = userRepo.getClass().getCanonicalName();
		Package package1 = userRepo.getClass().getPackage();
		System.out.println(canonicalName);
		System.out.println(package1);
	}

}
