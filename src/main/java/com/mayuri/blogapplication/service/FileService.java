package com.mayuri.blogapplication.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploadImage(String path, MultipartFile image);

    InputStream  getResources(String imageUploadPath, String imageName) throws FileNotFoundException;

}
