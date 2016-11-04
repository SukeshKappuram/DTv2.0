package com.devops.ecomerce.models.colabaration;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.devops.ecomerce.models.User;

@Entity
public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	@ManyToOne
	@NotNull
	private User user;
	@OneToMany
	@NotNull
	private Set<User> friends = new HashSet<User>(0);
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<User> getFriends() {
		return friends;
	}
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

}
