package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.uniovi.entities.types.FriendKey;

@Entity
@IdClass(FriendKey.class)
public class Friend {

    @Id
    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @Id
    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    protected Friend() {
    }

    public Friend(User user1, User user2) {
	this.user1 = user1;
	this.user2 = user2;
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
	Friend other = (Friend) obj;
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
