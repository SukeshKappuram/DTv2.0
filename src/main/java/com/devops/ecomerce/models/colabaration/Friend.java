package com.devops.ecomerce.models.colabaration;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.devops.ecomerce.models.User;

@Entity
public class Friend extends SocialNetwork{
	
	@NotNull
	private boolean accepted=false;
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.DELETE})
	private Set<User> Friends = new HashSet<User>(0);

	public Set<User> getFriends() {
		return Friends;
	}

	public void setFriends(Set<User> friends) {
		Friends = friends;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
}
