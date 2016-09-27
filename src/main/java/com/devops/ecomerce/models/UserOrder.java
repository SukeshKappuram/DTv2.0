package com.devops.ecomerce.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class UserOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderId;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id")
	private Cart cartId;
	@NotNull
	private Date orderDate;
	@NotNull
	private Date deliveryDate;
	@Column(columnDefinition="BOOLEAN default 'false'")
	private Boolean delivered;
	
	public Integer getId() {
		return orderId;
	}
	public void setId(Integer orderId) {
		this.orderId = orderId;
	}
	public Cart getCartId() {
		return cartId;
	}
	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Boolean getDelivered() {
		return delivered;
	}
	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}
}
