package com.devops.ecomerce.DAO;

import java.util.Date;
import java.util.List;


import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.ShippingAddress;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserRole;
import com.devops.ecomerce.models.WishList;
import com.devops.ecomerce.service.IUserService;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Service("userService")
public class UserDAOImpl implements IUserService {

	@Autowired
	private SessionFactory factory;
	
	private User user;

	//CRUD Operations for User
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public int addUser(User u) {
		System.out.println(isNewUser(u));
		if(!isNewUser(u)){
			Session session=factory.getCurrentSession();
			Transaction tx=session.beginTransaction();
		tx.begin();	
		session.saveOrUpdate(u);
		UserRole userRole=new UserRole();
		userRole.setRoleId(u);
		userRole.setRoleName("ROLE_USER");
		session.saveOrUpdate(userRole);
		Cart cart=new Cart();
		cart.setUserId(u);
		cart.setCartDate(new Date());
		session.saveOrUpdate(cart);
		WishList wishList =new WishList();
		wishList.setUserId(u);
		wishList.setWishDate(new Date());
		session.saveOrUpdate(wishList);
		tx.commit();
		return 1;
		}
		return 0;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public User verifyUser(User u) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(User.class);
		cr.add(Restrictions.eq("mailId",u.getMailId()));
		cr.add(Restrictions.eq("password",u.getPassword()));
		user=(User)cr.uniqueResult();
		tx.commit();
		return user;
	}

	public User getUser() {
		return user;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateUser(User user) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<User> viewUsers() {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(UserRole.class);
		cr.addOrder(Order.asc("roleId"));
		Criteria usCr = cr.createCriteria("roleId");
		List<User> users=usCr.list();
		tx.commit();
		return users;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean isNewUser(User user) {
		boolean newUser=false;
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(User.class);
		cr.add(Restrictions.eq("mailId",user.getMailId()));
		List<User> users=cr.list();
		System.out.println("Size "+users.size());
		if(users.size()>0)
		{
			newUser=true;
		}
		tx.commit();
		return newUser;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateRole(int roleId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(User.class);
		cr.add(Restrictions.eq("id",roleId));
		Criteria rcr=session.createCriteria(UserRole.class);
		rcr.add(Restrictions.eq("roleId",(User)cr.uniqueResult()));
		UserRole ur=(UserRole)rcr.uniqueResult();
		System.out.println(ur.getRoleId().getId());
		ur.setRoleName("ROLE_SELLER");
		session.update(ur);
		tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public String getRole() {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(UserRole.class);
		cr.add(Restrictions.eq("roleId",getUser()));
		UserRole ur=(UserRole)cr.uniqueResult();
		tx.commit();
		return ur.getRoleName();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public User loadUser(String mailId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(User.class);
		cr.add(Restrictions.eq("mailId",mailId));
		user=(User)cr.uniqueResult();
		tx.commit();
		return user;
	}

	//CRUD for Shipping Address 
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<ShippingAddress> viewShippingAddress() {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(ShippingAddress.class);
		cr.add(Restrictions.eq("userId",user));
		List<ShippingAddress> shippingAddress=cr.list();
		tx.commit();
		return shippingAddress;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public ShippingAddress getShippingAddress() {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(ShippingAddress.class);
		cr.add(Restrictions.eq("userId",user));
		cr.setFirstResult(1);
		ShippingAddress shippingAddress=(ShippingAddress)cr.uniqueResult();
		tx.commit();
		return shippingAddress;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public ShippingAddress getShippingAddress(User u) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(ShippingAddress.class);
		cr.add(Restrictions.eq("userId",u));
		cr.setFirstResult(1);
		ShippingAddress shippingAddress=(ShippingAddress)cr.uniqueResult();
		tx.commit();
		return shippingAddress;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addShippingAddress(ShippingAddress s) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(s);
		tx.commit();
	}

}
