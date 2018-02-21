package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.uniovi.model.types.FriendshipRequestKey;

@Entity
@IdClass(FriendshipRequestKey.class)
public class FriendshipRequest {

	@Id
	@ManyToOne
	private User user1;

	@Id
	@ManyToOne
	private User user2;

	protected FriendshipRequest() {
	}

	public FriendshipRequest(User user1, User user2) {
		Association.SendFriendRequest.link(user1, this, user2);
	}

	public User getUser1() {
		return user1;
	}

	protected void _setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	protected void _setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user1 == null) ? 0 : user1.hashCode());
		result = prime * result + ((user2 == null) ? 0 : user2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendshipRequest other = (FriendshipRequest) obj;
		if (user1 == null) {
			if (other.user1 != null)
				return false;
		} else if (!user1.equals(other.user1))
			return false;
		if (user2 == null) {
			if (other.user2 != null)
				return false;
		} else if (!user2.equals(other.user2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Friend [user1=" + user1 + ", user2=" + user2 + "]";
	}

}
