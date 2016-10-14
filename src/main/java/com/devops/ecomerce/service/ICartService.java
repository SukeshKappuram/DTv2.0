package com.devops.ecomerce.service;

import java.util.List;

import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.CartItem;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserOrder;

public interface ICartService {
	public void addToCart(CartItem cartItem);
	public void updateCart(Cart cart);
	public Cart getCart(Cart cart);
	public UserOrder addOrder(UserOrder order);
	public Cart getCart(User user);
	public List<CartItem> viewCart(User user);
	public List<CartItem> getCart(int productId,int cartId);
}