package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.repositories.FriendshipRequestRepository;

@Service
public class FriendshipRequestService {
	@Autowired
	private FriendshipRequestRepository friendshipRequestRepository;
	
	public List<FriendshipRequest> getFriendshipsRequests() {
		List<FriendshipRequest> request = new ArrayList<FriendshipRequest>();
		friendshipRequestRepository.findAll().forEach(request::add);
		return request;
	}

	public FriendshipRequest getFriendshipRequest(Long id) {
		return friendshipRequestRepository.findOne(id);
	}

	public void addFriendshipRequest(FriendshipRequest request) {
		friendshipRequestRepository.save(request);
	}

	public void deleteFriendshipRequest(Long id) {
		friendshipRequestRepository.delete(id);
	}
}
