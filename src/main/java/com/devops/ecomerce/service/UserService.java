/**
 * 
 */
package com.devops.ecomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.ecomerce.DAO.IUserDAO;
import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.models.User;

/**
 * @author iamsu
 *
 */
@Service("userService")
public class UserService implements IUserService {
	
	@Autowired(required=true)
	private IUserDAO iUserDAO;

	/* (non-Javadoc)
	 * @see com.devops.ecomerce.service.IUserService#addUser(com.devops.ecomerce.models.User)
	 */
	public int addUser(User user) {
		return iUserDAO.addUser(user);
	}

	/* (non-Javadoc)
	 * @see com.devops.ecomerce.service.IUserService#verifyUser(com.devops.ecomerce.models.User)
	 */
	public User verifyUser(User user) {
		return iUserDAO.verifyUser(user);
	}

	/* (non-Javadoc)
	 * @see com.devops.ecomerce.service.IUserService#getUser()
	 */
	public User getUser() {
		return iUserDAO.getUser();
	}

	/* (non-Javadoc)
	 * @see com.devops.ecomerce.service.IUserService#updateUser(com.devops.ecomerce.models.User)
	 */
	public void updateUser(User user) {
		iUserDAO.updateUser(user);
	}

	/* (non-Javadoc)
	 * @see com.devops.ecomerce.service.IUserService#viewUsers()
	 */
	public List<User> viewUsers() {
		return iUserDAO.viewUsers();
	}

	/* (non-Javadoc)
	 * @see com.devops.ecomerce.service.IUserService#viewShippingAddress()
	 */
	public List<ShippingAddress> viewShippingAddress() {
		return iUserDAO.viewShippingAddress();
	}

	public void addShippingAddress(ShippingAddress s) {
		// TODO Auto-generated method stub
		iUserDAO.addShippingAddress(s);
	}

	public void updateRole(int roleId) {
		// TODO Auto-generated method stub
		iUserDAO.updateRole(roleId);
	}

	public String getRole() {
		// TODO Auto-generated method stub
		return iUserDAO.getRole();
	}

	public ShippingAddress getShippingAddress() {
		// TODO Auto-generated method stub
		return iUserDAO.getShippingAddress();
	}

}
