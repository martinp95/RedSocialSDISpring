package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friend;
import com.uniovi.repositories.FriendsRepository;

@Service
public class FriendsService {
	@Autowired
	private FriendsRepository friendshipsRepository;
	
	public List<Friend> getFriendships() {
		List<Friend> friendship = new ArrayList<Friend>();
		friendshipsRepository.findAll().forEach(friendship::add);
		return friendship;
	}

	public Friend getFriendship(Long id) {
		return friendshipsRepository.findOne(id);
	}

	public void addFriendship(Friend friendship) {
		friendshipsRepository.save(friendship);
	}

	public void deleteFriendship(Long id) {
		friendshipsRepository.delete(id);
	}
	
	public void deleteFriendship(Friend friendship) {
		friendshipsRepository.delete(friendship);
	}
	
	
}
