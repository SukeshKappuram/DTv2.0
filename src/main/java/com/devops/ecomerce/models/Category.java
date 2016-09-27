package com.devops.ecomerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@Size(min=4,max=30,message="Category Should atleast have 4 Char")
	private String name;
	@NotNull
	private String description;
	@Transient
	private MultipartFile categoryImage;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public MultipartFile getProductImage() {
		return categoryImage;
	}
	public void setProductImage(MultipartFile categoryImage) {
		this.categoryImage = categoryImage;
	}	
	
}
