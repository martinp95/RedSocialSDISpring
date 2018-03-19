package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friend;
import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;
import com.uniovi.repositories.FriendsRepository;
import com.uniovi.repositories.FriendshipRequestRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private FriendsRepository friendshipsRepository;

	@Autowired
	private FriendshipRequestRepository friendshipRequestRepository;

	public User getUser(Long id) {
		return usersRepository.findOne(id);
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		// borar peticiones de amistad enviadas a este usuario
		List<FriendshipRequest> frR = friendshipRequestRepository.findAllByUser(id);
		friendshipRequestRepository.delete(frR);
		// borrar relacion de amistad donde este como segundo.
		List<Friend> fr = friendshipsRepository.findAllByUser(id);
		friendshipsRepository.delete(fr);
		
		usersRepository.delete(id);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public Page<User> findAll(Pageable pageable, Long id) {
		return usersRepository.findAll(pageable, id);
	}

	public Page<User> searchUsersByNameAndEmail(Pageable pageable, String searchText, Long id) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%" + searchText + "%";
		users = usersRepository.searchByNameAndEmail(pageable, searchText, id);
		return users;
	}
}
