package com.devops.ecomerce.service;

import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import com.devops.ecomerce.models.SocialNetwork;


public interface IUtilityService {
	public String getJson(List items);
	public String getImage(String folderName,int id);
	public void setErrors(List<ObjectError> errors);
	public List<ObjectError> getErrors();
	public void uploadImage(MultipartFile file,SocialNetwork s);
}
