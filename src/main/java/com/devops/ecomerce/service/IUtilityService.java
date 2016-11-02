package com.devops.ecomerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import com.devops.ecomerce.models.colabaration.SocialNetwork;


public interface IUtilityService {
	public String getJson(List items);
	public String getImage(String folderName,int id);
	public void setErrors(List<ObjectError> errors);
	public List<ObjectError> getErrors();
	public void uploadImage(MultipartFile file,SocialNetwork s);
	public double findDistance(long... pin) throws IOException;
	public String uploadImage(MultipartFile image,String fileName,String folder,int imageId);
}
