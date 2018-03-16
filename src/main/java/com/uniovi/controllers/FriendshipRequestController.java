package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Friend;
import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;
import com.uniovi.services.FriendsService;
import com.uniovi.services.FriendshipRequestService;
import com.uniovi.services.UsersService;

@Controller
public class FriendshipRequestController {

	@Autowired
	private FriendshipRequestService friendshipRequestService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private FriendsService friendsService;

	@RequestMapping(value = "/friendshipRequest/add/{id}")
	public String addFriendshipRequest(Model model, Pageable pageable, @PathVariable Long id, Principal principal) {
		String email = principal.getName();
		User user1 = usersService.getUserByEmail(email);
		User user2 = usersService.getUser(id);
		FriendshipRequest original = new FriendshipRequest(user1, user2);

		if (user1.getFriendsRequest().contains(original)) {
			model.addAttribute("errorEnviado", user2.getId());
		} else {
			friendshipRequestService.addFriendshipRequest(original);
		}

		Page<User> users = usersService.findAll(pageable, user1.getId());
		model.addAttribute("usersList", users.getContent());
		return "user/listUsuarios ::  tableListUsers";
	}

	@RequestMapping(value = "/friendshipRequest/accept/{id}")
	public String acceptFriendshipRequest(@PathVariable Long id, Principal principal) {
		String email = principal.getName();
		User user2 = usersService.getUserByEmail(email);

		User user1 = usersService.getUser(id);

		FriendshipRequest request = friendshipRequestService.getFriendshipRequestByUsers(user1, user2);

		friendshipRequestService.deleteFriendshipRequest(request);

		Friend friendship1 = new Friend(user1, user2);
		Friend friendship2 = new Friend(user2, user1);

		friendsService.addFriendship(friendship1);
		friendsService.addFriendship(friendship2);

		return "redirect:/friendshipRequest/listRequest";
	}

	@RequestMapping("/friendshipRequest/listRequest")
	public String getListado(Model model, Pageable pageable, Principal principal) {
		Page<FriendshipRequest> requests = new PageImpl<FriendshipRequest>(new LinkedList<FriendshipRequest>());

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);

		requests = friendshipRequestService.getFriendshipRequestsForUser(pageable, user);

		model.addAttribute("requestList", requests.getContent());
		model.addAttribute("page", requests);
		return "friendshipRequest/listRequest";
	}

	@RequestMapping("/friendshipRequest/listRequest/update")
	public String updateListado(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);

		Page<FriendshipRequest> requests = friendshipRequestService.getFriendshipRequestsForUser(pageable, user);
		model.addAttribute("requestList", requests.getContent());
		return "friendshipRequest/listRequest :: tableFriendshipRequest";
	}

}
