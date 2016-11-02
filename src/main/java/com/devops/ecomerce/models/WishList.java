package com.devops.ecomerce.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class WishList {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	@NotNull
	@ManyToOne
	private User userId;
	@Column(columnDefinition="BOOLEAN default 'false'")
	private Boolean purchased=false;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="Date default getDate()")
	private Date wishDate=new Date();
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.DELETE})
	private Set<ListItem> listItems = new HashSet<ListItem>(0);
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Boolean getPurchased() {
		return purchased;
	}
	public void setPurchased(Boolean purchased) {
		this.purchased = purchased;
	}
	public Date getWishDate() {
		return wishDate;
	}
	public void setWishDate(Date wishDate) {
		this.wishDate = wishDate;
	}
	public Set<ListItem> getListItems() {
		return listItems;
	}
	public void setListItems(Set<ListItem> listItems) {
		this.listItems = listItems;
	}

	
}
