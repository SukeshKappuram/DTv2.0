package com.devops.ecomerce.models.colabaration;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Share extends SocialNetwork {
	@Size(min=10,message="Blog Description should have atleast 20 letters")
	private String description;
	@Transient 
	private MultipartFile image; 
	
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
