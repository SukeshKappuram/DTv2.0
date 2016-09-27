package com.devops.ecomerce.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class CartItem {
	@EmbeddedId
	CartGroup cartGroupId;
	@NotNull
	private Integer quantity;
	@NotNull
	private Float totatPrice;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Float getTotatPrice() {
		return totatPrice;
	}
	public void setTotatPrice(Float totatPrice) {
		this.totatPrice = totatPrice;
	}
	public CartGroup getCartGroupId() {
		return cartGroupId;
	}
	public void setCartGroupId(CartGroup cartGroupId) {
		this.cartGroupId = cartGroupId;
	}
	
	
}
