package com.uniovi.entities;

public class Association {

	public static class Publish {

		public static void link(User user, Publication publication) {
			publication._setUser(user);
			user._getPublications().add(publication);
		}

		public static void unlink(User user, Publication publication) {
			user._getPublications().remove(publication);
			publication._setUser(null);
		}

	}

	public static class BecomeFriends {

		public static void link(User user1, Friend friend, User user2) {
			friend._setUser1(user1);
			friend._setUser2(user2);

			user1._getFriends().add(friend);
			user2._getFriends().add(friend);
		}

	}
	
	public static class SendFriendRequest {

		public static void link(User user1, FriendshipRequest friendshipRequest, User user2) {
			friendshipRequest._setUser1(user1);
			friendshipRequest._setUser2(user2);

			user1._getFriendsRequest().add(friendshipRequest);
			user2._getFriendsRequest().add(friendshipRequest);
		}

	}

}
