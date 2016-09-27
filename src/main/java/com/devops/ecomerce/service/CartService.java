package com.devops.ecomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.ecomerce.DAO.ICartDAO;
import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.CartItem;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserOrder;

@Service("cartService")
public class CartService implements ICartService {

	@Autowired(required=true)
	private ICartDAO iCartDAO;
	
	public void addToCart(CartItem cartItem) {
		iCartDAO.addToCart(cartItem);
	}

	public void updateCart(Cart cart) {
		iCartDAO.updateCart(cart);
	}

	public Cart getCart(Cart cart) {
		return iCartDAO.getCart(cart);
	}

	public UserOrder addOrder(Cart cart) {
		return iCartDAO.addOrder(cart);
	}
	
	public Cart getCart(User user){
		return iCartDAO.getCart(user);
	}
	
	public List<CartItem> viewCart(User user){
		return iCartDAO.viewCart(user);
	}

}
