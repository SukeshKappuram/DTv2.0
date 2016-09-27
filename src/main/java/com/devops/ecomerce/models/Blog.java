package com.devops.ecomerce.models;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Blog extends SocialNetwork {
	@Size(min=4,max=20,message="Blog Name should atleast contain 4 letters")
	private String name;
	@Size(min=20,message="Blog Description should have atleast 20 letters")
	private String description;
	@Transient 
	private MultipartFile image; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
}
