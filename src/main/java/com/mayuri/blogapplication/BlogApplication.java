package com.mayuri.blogapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args)  {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
    System.out.println(this.passwordEncoder.encode("Nikita@123"));		
	}
	
	//mayuri@gamil.com = Mayu@123
	//kapil@gmail.com = kapil@12
	//uday@gmail.com = uday@123

}
