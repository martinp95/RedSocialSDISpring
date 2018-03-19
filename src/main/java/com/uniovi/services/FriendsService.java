package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friend;
import com.uniovi.entities.User;
import com.uniovi.repositories.FriendsRepository;

@Service
public class FriendsService {

	@Autowired
	private FriendsRepository friendshipsRepository;

	public void addFriendship(Friend friendship) {
		friendshipsRepository.save(friendship);
	}

	public void deleteFriendship(Friend friendship) {
		friendshipsRepository.delete(friendship);
	}

	public Page<Friend> getFriendshipForUser(Pageable pageable, User user) {
		Page<Friend> request = new PageImpl<Friend>(new LinkedList<Friend>());
		request = friendshipsRepository.findAllByUser(pageable, user);
		return request;
	}
}
