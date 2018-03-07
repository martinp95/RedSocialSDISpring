package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;
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

	public Page<FriendshipRequest> getFriendshipRequestsForUser(Pageable pageable, User user) {
		Page<FriendshipRequest> request = new PageImpl<FriendshipRequest>(new LinkedList<FriendshipRequest>());
		request =  friendshipRequestRepository.findAllByUser(pageable, user);
		return request;
	}
	
	public void deleteFriendshipRequest(FriendshipRequest request) {
		friendshipRequestRepository.delete(request);
	}
	
	public FriendshipRequest getFriendshipRequestByUsers(User user1, User user2) {
		return friendshipRequestRepository.findByUsers(user1, user2);
	}
	
}
