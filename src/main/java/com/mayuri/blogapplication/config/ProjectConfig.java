package com.mayuri.blogapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mayuri.blogapplication.service.impl.FileServiceImpl;

import org.modelmapper.ModelMapper;


@Configuration
public class ProjectConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

}
