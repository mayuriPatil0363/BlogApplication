package com.mayuri.blogapplication.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mayuri.blogapplication.exception.BadApiRequestException;
import com.mayuri.blogapplication.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	

	@Override
	public String uploadImage(String path, MultipartFile file) {
		
//		String originalFileName = file.getName();
//		String randomFileName = UUID.randomUUID().toString();
//		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
//		String fileName = path + randomFileName + extension;
//
//		if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".jpg")) {
//		File folder = new File(path);
//		if(!folder.exists()) {
//			
//			folder.mkdirs();
//		}
//		try {
//			Files.copy(file.getInputStream(), Paths.get(fileName));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return fileName;
//		
//		}else {
//			throw new BadApiRequestException("File with this "+ extension +" not allowed !!");
//		}
//		
		
		 String originalfileName =file.getOriginalFilename();
	        //logger.info("FileName : {}", originalfileName);
	        String fileName = UUID.randomUUID().toString();
	        String extension = originalfileName.substring(originalfileName.lastIndexOf("."));
	        String fileNameWithExtension = fileName+extension;
	        String fullPathWithFileName = path + fileNameWithExtension;

	        //logger.info("image path : {}" ,fullPathWithFileName);
	        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))
	        {
	            //File creation
	            //logger.info("File Extension is {}", extension);
	            File folder =new File(path);
	            if(!folder.exists()){

	                //create the folder
	                folder.mkdirs();
	            }
	            try {
					Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return fileNameWithExtension;

	        }else{
	            throw new BadApiRequestException("File with this" + extension + "not allowed !!");
	        }
	}
	
	
	public InputStream getResources(String path, String name) throws FileNotFoundException {

        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }


	
}


