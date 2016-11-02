package com.devops.ecomerce.service;

import java.util.List;

import com.devops.ecomerce.models.Cart;
import com.devops.ecomerce.models.CartItem;
import com.devops.ecomerce.models.ListItem;
import com.devops.ecomerce.models.User;
import com.devops.ecomerce.models.UserOrder;
import com.devops.ecomerce.models.WishList;

public interface ICartService {
	public void addCartItem(CartItem cartItem);
	public void deleteCartItem(int cartId,int productId);
	public void deleteCart(int cartId);
	public Cart getCart(User user);
	public void updateCart(Cart cart);
	
	public void addWishListItem(ListItem listItem);
	public void addWishList(WishList wishList);
	public WishList getWishList(User user);
	public void deleteWishList(int wishListId);
	public void deleteWishListItem(int wishListId,int productId);
	
	public UserOrder addOrder(UserOrder order);
	
	public List<CartItem> viewCart(User user);
	public List<CartItem> getCart(int productId,int cartId);
	
}