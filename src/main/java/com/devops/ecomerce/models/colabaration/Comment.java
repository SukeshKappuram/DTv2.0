package com.devops.ecomerce.models.colabaration;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.devops.ecomerce.models.User;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Size(min=1,message="comment should have atleast 1 letter")
	private String description;
	@ManyToOne
	private User user;
	@NotNull
	private Date createdDate;
	@Transient 
	private MultipartFile image; 
	@ManyToOne
	private SocialNetwork networkId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public SocialNetwork getNetworkId() {
		return networkId;
	}
	public void setNetworkId(SocialNetwork networkId) {
		this.networkId = networkId;
	}
	
	
}
