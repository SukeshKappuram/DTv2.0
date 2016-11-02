package com.devops.ecomerce.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class WishGroup implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull
	@ManyToOne
	private WishList wishList;
	@NotNull
	@ManyToOne
	private Product product;
	
	public WishList getWishList() {
		return wishList;
	}
	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
