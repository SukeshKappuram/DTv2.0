package com.devops.ecomerce.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productId;
	@Pattern(regexp = "(\n|^).*?(?=\n|$)")
	@Size(min=4,max=20,message="Product Name Should atleast have 4 Char")
	private String name;
	@NotNull
	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Category categoryId;
	@NotNull
	private Float price;
	@Pattern(regexp = "(\n|^).*?(?=\n|$)")
	@Size(min=10,max=50,message="Description Should atleast have 10 Char")
	private String description;
	@Column(columnDefinition="Integer default 0")
	private Integer available;
	@Transient
	private MultipartFile productImage;
	@Column(columnDefinition="Date default getDate()")
	private Date dated; 
	
	public Integer getProductId() {
		return productId;
	}
	public void setId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getProductImage() {
		return productImage;
	}
	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Date getDated() {
		return dated;
	}
	public void setDated(Date dated) {
		this.dated = dated;
	}
	
}
