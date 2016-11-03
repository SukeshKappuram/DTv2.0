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
import com.devops.ecomerce.models.ListItem;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserOrder;
import com.devops.ecomerce.models.WishList;
import com.devops.ecomerce.service.ICartService;

@Repository
@Service("cartService")
public class CartDAOImpl implements ICartService {

	@Autowired
	private SessionFactory factory;
	
	//Wishlist
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addWishListItem(ListItem listItem) {
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(listItem);
		tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addWishList(WishList wishList){
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(wishList);
		tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public WishList getWishList(User user) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(WishList.class);
		cr.add(Restrictions.eq("userId",user));
		WishList wishList=(WishList)cr.uniqueResult();
		tx.commit();
		return wishList;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void deleteWishList(int wishListId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(ListItem.class);
		cr.add(Restrictions.eq("wishGroup.wishList.Id",wishListId));
		List<ListItem> items = cr.list();
		WishList wishes = (WishList)session.get(WishList.class, new Integer(wishListId));
		for(ListItem item:items){
			wishes.getListItems().remove(item);
		}
		session.saveOrUpdate(wishes);
		tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void deleteWishListItem(int wishListId,int productId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(ListItem.class);
		cr.add(Restrictions.eq("wishGroup.wishList.Id",wishListId));
		cr.add(Restrictions.eq("wishGroup.product.productId",productId));
		ListItem item = (ListItem)cr.list().get(0);
		WishList wish = (WishList)session.get(WishList.class, new Integer(wishListId));
		wish.getListItems().remove(item);
		session.saveOrUpdate(wish);
		tx.commit();
	}
	
	//CRUD for Cart
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addCartItem(CartItem cartItem) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(cartItem);
		System.out.println(cartItem.getTotatPrice()+" added");
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
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void deleteCart(int cartId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(CartItem.class);
		cr.add(Restrictions.eq("cartGroupId.cartId.cartId",cartId));
		List<CartItem> items = cr.list();
		Cart cart = (Cart)session.get(Cart.class, new Integer(cartId));
		for(CartItem item:items){
		cart.getCartItems().remove(item);
		}
		session.saveOrUpdate(cart);
		tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void deleteCartItem(int cartId,int productId) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(CartItem.class);
		cr.add(Restrictions.eq("cartGroupId.cartId.cartId",cartId));
		cr.add(Restrictions.eq("cartGroupId.productId.productId",productId));
		CartItem item = (CartItem)cr.list().get(0);
		Cart cart = (Cart)session.get(Cart.class, new Integer(cartId));
		cart.getCartItems().remove(item);
		session.saveOrUpdate(cart);
		tx.commit();
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

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<CartItem> viewCart(User user) {
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(CartItem.class).add(Restrictions.eq("cartGroupId.cartId", getCart(user)));
		List<CartItem> cartItems=(List<CartItem>)cr.list();
		tx.commit();
		return cartItems;
	}
	
	//CRUD on Orders
	
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
	public List getSellerOrders(){
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria cr=session.createCriteria(UserOrder.class);
		List<CartItem> cartItems=cr.list();
		tx.commit();
		return cartItems;
	}

		
}
