package com.devops.ecomerce.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@Column(unique=true)
	@Size(min=4,max=30,message="Category Should atleast have 4 Char")
	@Pattern(regexp = "(\n|^).*?(?=\n|$)")
	private String name;
	@Size(min=4,max=30,message="Description Should atleast have 4 Char")
	@Pattern(regexp = "(\n|^).*?(?=\n|$)")
	private String description;
	@Transient
	private MultipartFile categoryImage;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="Date default getDate()")
	private Date dated;
	
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
	public MultipartFile getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(MultipartFile categoryImage) {
		this.categoryImage = categoryImage;
	}
	public Date getDated() {
		return dated;
	}
	public void setDated(Date dated) {
		this.dated = dated;
	}	
	
}
