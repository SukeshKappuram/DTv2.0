package com.devops.ecomerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	@ManyToOne
	private User userId;
	@NotEmpty(message="Reciever can not be empty")
	@Pattern(regexp = "[a-zA-Z@=\\-'\"]+")
	private String receiverName;
	@NotEmpty(message="Door No can not be empty")
	@Pattern(regexp = "[a-zA-Z0-9@=\\-'\"]+")
	private String doorNo;
	@NotEmpty(message="Street No can not be empty")
	@Pattern(regexp = "[a-zA-Z0-9@=\\-'\"]+")
	private String street;
	@NotEmpty(message="location can not be empty")
	@Pattern(regexp = "[a-zA-Z0-9@=\\-'\"]+")
	private String location;
	@NotEmpty(message="city can not be empty")
	@Pattern(regexp = "[a-zA-Z@=\\-'\"]+")
	private String city;
	@Pattern(regexp = "[a-zA-Z0-9@=\\-'\"]+")
	@NotEmpty(message="state can not be empty")
	private String state;
	@Pattern(regexp = "[0-9]+")
	@NotNull(message="Pincode can not be empty")
	private Integer pincode;
	@Pattern(regexp = "[a-zA-Z0-9@=\\-'\"]+")
	@NotEmpty(message="Address can not be empty")
	private String description;
	@Pattern(regexp = "[0-9]10")
	@Size(min=10,max=10,message="Phone can not be empty")
	private String phoneNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
}
