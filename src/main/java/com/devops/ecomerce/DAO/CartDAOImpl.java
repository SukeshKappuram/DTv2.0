package com.devops.ecomerce.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.CartItem;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserOrder;

@Repository("Cart")
public class CartDAOImpl implements ICartDAO {

	@Autowired
	private SessionFactory factory;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addToCart(CartItem cartItem) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(cartItem);
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateCart(Cart cart) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(cart);
		tx.commit();
	}

	public Cart getCart(Cart cart) {
		return cart;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Cart getCart(User user) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(Cart.class);
		cr.add(Restrictions.eq("userId",user));
		Cart cart=(Cart)cr.uniqueResult();
		tx.commit();
		return cart;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public UserOrder addOrder(Cart cart) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(cart);
		tx.commit();
		return new UserOrder();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<CartItem> viewCart(User user) {
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(CartItem.class).add(Restrictions.eq("cartGroupId.cartId", getCart(user)));
		List<CartItem> cartItems=cr.list();
		tx.commit();
		return cartItems;
	}

}
