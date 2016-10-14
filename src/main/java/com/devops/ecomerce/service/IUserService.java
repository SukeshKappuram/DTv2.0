package com.devops.ecomerce.service;

import java.util.List;

import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.models.User;

public interface IUserService {
	public int addUser(User user);
	public void addShippingAddress(ShippingAddress s);
	public User verifyUser(User user);
	public User loadUser(String mailId);
	public User getUser();
	public void updateUser(User user);
	public List<User> viewUsers();
	public List<ShippingAddress> viewShippingAddress();
	public void updateRole(int roleId);
	public String getRole();
	public ShippingAddress getShippingAddress();
}
