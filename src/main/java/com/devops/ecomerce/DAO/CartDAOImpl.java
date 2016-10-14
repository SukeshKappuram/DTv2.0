package com.devops.ecomerce.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.CartItem;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserOrder;
import com.devops.ecomerce.service.ICartService;

@Repository
@Service("cartService")
public class CartDAOImpl implements ICartService {

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
	public UserOrder addOrder(UserOrder order) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(order);
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
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List getSellerOrders(){
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(UserOrder.class);
		List<CartItem> cartItems=cr.list();
		tx.commit();
		return cartItems;
	}
	
	public List<CartItem> getCart(int productId,int cartId){
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		System.out.println("==START==");
		Criteria cr=session.createCriteria(CartItem.class).add(Restrictions.eq("cartGroupId.productId.productId",productId));
		cr.add(Restrictions.eq("cartGroupId.cartId.cartId",cartId));
		List<CartItem> cartItems=cr.list();
		System.out.println("==START==");
		for(CartItem c:cartItems){
			System.out.println(c.getTotatPrice());
		}
		System.out.println("==END==");
		System.out.println("==END==");
		tx.commit();
		return cartItems;
	}

}
