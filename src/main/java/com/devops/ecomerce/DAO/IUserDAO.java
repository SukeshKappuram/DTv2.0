package com.devops.ecomerce.DAO;

import java.util.List;

import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.models.User;

public interface IUserDAO {
	public int addUser(User user);
	public void addShippingAddress(ShippingAddress s);
	public User verifyUser(User user);
	public User getUser();
	public void updateUser(User user);
	public List<User> viewUsers();
	public List<ShippingAddress> viewShippingAddress();
	public boolean isNewUser(User user);
	public void updateRole(int roleId);
	public String getRole();
	public ShippingAddress getShippingAddress();
}
