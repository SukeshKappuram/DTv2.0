package com.devops.ecomerce.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class CartGroup implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull
	@ManyToOne
	private Cart cartId;
	@NotNull
	@ManyToOne
	private Product productId;
	
	public Cart getCartId() {
		return cartId;
	}
	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	
	
}
