package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String password;
	private boolean admin;

	@Transient 
	private String passwordConfirm;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Publication> publications = new HashSet<Publication>();

	@OneToMany(mappedBy = "user1")
	private Set<Friend> friends = new HashSet<Friend>();

	@OneToMany(mappedBy = "user1")
	private Set<FriendshipRequest> friendsRequest = new HashSet<FriendshipRequest>();

	public User() {
	}

	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	protected Set<Publication> _getPublications() {
		return publications;
	}

	public Set<Publication> getPublications() {
		return new HashSet<Publication>(publications);
	}

	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}

	protected Set<Friend> _getFriends() {
		return friends;
	}

	public Set<Friend> getFriends() {
		return new HashSet<Friend>(friends);
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}

	protected Set<FriendshipRequest> _getFriendsRequest() {
		return friendsRequest;
	}

	public Set<FriendshipRequest> getFriendsRequest() {
		return new HashSet<FriendshipRequest>(friendsRequest);
	}

	protected void _setFriendsRequest(Set<FriendshipRequest> friendsRequest) {
		this.friendsRequest = friendsRequest;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + "]";
	}

	public boolean isAdmin() {
		return admin;
	}
}

