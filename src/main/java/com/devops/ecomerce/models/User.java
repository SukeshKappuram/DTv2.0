package com.devops.ecomerce.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Pattern(regexp = "[a-zA-Z@=\\-'\"]+")
	@Size(min=4,max=20,message="FirstName Should atleast have 4 Char")
	private String firstName;
	@Pattern(regexp = "[a-zA-Z@=\\-'\"]+")
	@Size(min=4,max=20,message="LastName Should atleast have 4 Char")
	private String lastName;
	@Pattern(regexp = "[0-9]10")
	@Size(min=10,max=10,message="Invalid Phone Number")
	private String phoneNumber;
	@Email(message="Invalid Email Id")
	private String mailId;
	@Pattern(regexp = "[a-zA-Z0-9@=\\-'\"]+")
	@Size(min=6,max=20,message="Password Should atleast have 4 Char")
	private String password;
	@NotNull
	private Date registeredDate=new Date();
	@NotNull
	private Boolean enabled=true;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
}
