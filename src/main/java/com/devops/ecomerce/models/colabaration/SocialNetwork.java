package com.devops.ecomerce.models.colabaration;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.devops.ecomerce.models.User;

@Entity
public class SocialNetwork {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	@NotNull
	@ManyToOne
	protected User user;
	@NotNull
	protected Date createdDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
}
